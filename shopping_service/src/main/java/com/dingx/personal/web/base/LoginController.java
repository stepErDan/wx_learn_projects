package com.dingx.personal.web.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.dingx.personal.common.annontation.IgnoreLogin;
import com.dingx.personal.entity.sys.SysUser;
import com.dingx.personal.entity.sys.SysUserTemp;
import com.dingx.personal.service.base.ITokenService;
import com.dingx.personal.service.sys.ISysUserService;
import com.dingx.personal.service.sys.ISysUserTempService;
import com.dingx.personal.vo.common.CommonStr;
import com.dingx.personal.vo.common.Result;
import com.dingx.personal.vo.wx.WxReq;
import com.dingx.personal.vo.wx.WxRes;
import com.dingx.personal.vo.wx.WxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.dingx.personal.common.constant.GeneralAttributes.RESULT_SUCCESS;

/**
 * 2020年5月11日 22:12:49
 */
@RequestMapping("rest/login")
@RestController
@Api("用户登录")
@Slf4j
public class LoginController extends BaseController {
    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysUserTempService iSysUserTempService;

    @Autowired
    private ITokenService iTokenService;

    @Value("${wx.api.appid}")
    private static String appid;

    @Value("${wx.api.secret}")
    private static String secret;

    @Value("${wx.api.loginUrl}")
    private static String loginUrl;


    /**
     * 登录
     * @param openid
     * @return
     */
    @ApiOperation(value = "登陆系统", notes = "登陆系统")
    @PostMapping("/getSysToken")
    @IgnoreLogin
    public Result getSysToken(@RequestBody CommonStr openid){
        //根据opid获取对应的
        SysUser user = iSysUserService.getByOpenid(openid.getStr());
        //未获得用户
        if(null == user){
            return Result.error("未获得此用户注册信息！");
        }
        String token = iTokenService.getToken(user);
        if (StringUtils.isNotBlank(token)){
            return Result.success(token);
        }else {
            return Result.error("未获得此用户注册信息！");
        }
    }

    /**
     * 通过Code获取微信token等信息
     * @param code
     * @return
     */
    @ApiOperation(value = "通过Code获取微信token等信息", notes = "登陆系统")
    @PostMapping("/getTokenByCode")
    @IgnoreLogin
    public Result getTokenByCode(@RequestBody CommonStr code){
        //配置
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        WxReq req = new WxReq();
        req.setAppid(appid);
        req.setSecret(secret);
        req.setJs_code(code.getStr());
        JSONObject object = HttpUtilController.httpGetRequestMethod("http://127.0.0.1:8890/rest/login/reqSession",req);
        WxRes res = JSON.parseObject(object.toJSONString(),WxRes.class);
        //验证用户是否已存在
        SysUser user = null;
        if(iSysUserService.checkOpenid(res.getOpenid())){
            //用户存在则直接获取用户
            user = iSysUserService.getByOpenid(res.getOpenid());
        }
        //临时用户登陆对象
        SysUserTemp userTemp = new SysUserTemp(res);
        int num = iSysUserTempService.updateByOpenid(userTemp);
        //若num为0，意味着数据库中并没有此数据，新建一条
        if (num == 0){
            iSysUserTempService.save(userTemp);
        }
        return new Result(res.getOpenid(),user);
    }

    /**
     * 注册
     * @param wxUser
     * @return
     */
    @ApiOperation(value = "注册用户", notes = "注册")
    @PostMapping("/registe")
    @IgnoreLogin
    public Result registe(@RequestBody WxUser wxUser, HttpServletRequest request){
        //从header中取出openid
        String openid = request.getHeader("wx-openid");
        SysUser user = new SysUser();
        BeanUtils.copyProperties(wxUser,user);
        user.setOpenid(openid);
        return iSysUserService.saveUser(user);
    }

    /**
     * 通过用户生成token
     * @param user
     * @return
     */
    @ApiOperation(value = "通过用户生成token", notes = "生成token")
    @PostMapping("/getTokenByUser")
    @IgnoreLogin
    public Result getTokenByUser(@RequestBody SysUser user){
        // 生成 jwt 票据，访问请求头设置‘ accessToken=票据内容 ’
        String jwtToken = SSOToken.create().setId(user.getId()).setIssuer(user.getNickName()).getToken();
        SysUserTemp temp = new SysUserTemp().setOpenid(user.getOpenid()).setToken(jwtToken);
        iSysUserTempService.updateByOpenid(temp);
        return new Result(jwtToken);
    }

    /**
     * 校验token
     * @param str
     * @return
     */
    @ApiOperation(value = "校验token", notes = "校验token")
    @PostMapping("/checkToken")
    public Result checkToken(@RequestBody CommonStr str) throws Exception {
        return new Result(iTokenService.checkToken(str.getStr()));
    }

    //——————————————————————————————————————————————————————————————————

    /**
     * 模拟微信返回
     * @return
     */
    @ApiOperation(value = "模拟微信返回", notes = "登陆系统")
    @GetMapping("/reqSession")
    @IgnoreLogin
    public WxRes reqSession(@Param("appid") String appid,
                            @Param("grant_type") String grant_type,
                            @Param("js_code") String js_code,
                            @Param("secret") String secret){
        WxRes res = new WxRes();
        res.setOpenid("12345");
        res.setSession_key("session");
        res.setUnionid("???");
        res.setErrcode(RESULT_SUCCESS);
        res.setErrmsg("没毛病");
        return res;
    }
}
