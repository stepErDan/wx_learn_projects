package com.dingx.personal.web.sys;

import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import com.dingx.personal.entity.sys.SysUser;
import com.dingx.personal.service.sys.ISysUserService;
import com.dingx.personal.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 2020年5月11日 22:12:49
 */
@RequestMapping("/rest/sysuser")
@RestController
@Api("用户管理")
@Slf4j
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService iSysUserService;

    @ResponseBody
    @PostMapping("/getDetail")
    @ApiOperation(value="用户管理-获取详情", notes = "")
    public SysUser getDetail(@RequestBody Long id){
        return iSysUserService.getDetail(id);
    }

    @ResponseBody
    @PostMapping("/save")
    @ApiOperation(value="用户管理-保存用户", notes = "")
    public boolean save(@RequestBody SysUser sysUser){
        return iSysUserService.save(sysUser);
    }

    @ResponseBody
    @PostMapping("/update")
    @ApiOperation(value="用户管理-修改用户", notes = "")
    public boolean update(@RequestBody SysUser sysUser){
        return iSysUserService.updateById(sysUser);
    }
}
