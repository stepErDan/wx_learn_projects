package com.dingx.personal.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingx.personal.entity.sys.SysUserTemp;

/**
 * 用户临时登陆Service
 * 2020年5月11日 22:14:51
 */
public interface ISysUserTempService extends IService<SysUserTemp> {
    int updateByOpenid(SysUserTemp userTemp);

    /**
     * 根据token获取对应的临时对象
     * @param token
     * @return
     */
    SysUserTemp getByToken(String token);
}
