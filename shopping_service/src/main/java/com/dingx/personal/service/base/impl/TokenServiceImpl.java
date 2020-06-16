package com.dingx.personal.service.base.impl;

import com.baomidou.kisso.security.token.SSOToken;
import com.dingx.personal.entity.sys.SysUser;
import com.dingx.personal.entity.sys.SysUserTemp;
import com.dingx.personal.service.base.ITokenService;
import com.dingx.personal.service.sys.ISysUserTempService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class TokenServiceImpl  implements ITokenService {
    @Value("${token.timeout}")
    public Long tokenTime;

    private final static Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private ISysUserTempService iSysUserTempService;

    @Override
    public String getToken(SysUser user) {
        // 生成 jwt 票据，访问请求头设置‘ accessToken=票据内容 ’
        String jwtToken = SSOToken.create().setId(user.getId()).setIssuer(user.getNickName()).getToken();
        //
        SysUserTemp temp = new SysUserTemp();
        temp.setOpenid(user.getOpenid());
        temp.setCreateDate(LocalDateTime.now());
        temp.setToken(jwtToken);
        int i = iSysUserTempService.updateByOpenid(temp);
        if(i > 0){
            return jwtToken;
        }else{
            return null;
        }
    }

    @Override
    public boolean checkToken(String token) throws Exception {
        SysUserTemp sysUserTemp = iSysUserTempService.getByToken(token);
        if (null == sysUserTemp){
            throw new Exception("无此token");
        }
        //获取当前时间，校验是否过期
        LocalDateTime cTime = sysUserTemp.getCreateDate();
        //比较时间差
        Long time = ChronoUnit.SECONDS.between(cTime,LocalDateTime.now());
        if (time > tokenTime){
            return false;
        }
        return true;
    }
}
