package com.dingx.personal.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingx.personal.entity.sys.SysUser;
import com.dingx.personal.entity.sys.SysUserTemp;

/**
 * Token Service
 * 2020年5月11日 22:14:51
 */
public interface ITokenService{
    /**
     * 获取token
     * @param user
     * @return
     */
    String getToken(SysUser user);

    /**
     * 校验token是否有效
     * @param token
     * @return
     */
    boolean checkToken(String token) throws Exception;
}
