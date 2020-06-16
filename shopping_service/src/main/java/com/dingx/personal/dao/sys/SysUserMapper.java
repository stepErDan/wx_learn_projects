package com.dingx.personal.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingx.personal.entity.sys.SysUser;
import com.dingx.personal.entity.sys.SysUserBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface SysUserMapper extends BaseMapper<SysUser> {
    int checkOpenid(String openid);

    int saveBaseUser(@Param("userBase") SysUserBase userBase);
}
