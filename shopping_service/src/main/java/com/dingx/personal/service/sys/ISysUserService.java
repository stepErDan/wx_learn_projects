package com.dingx.personal.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingx.personal.entity.sys.SysUser;
import com.dingx.personal.vo.common.Result;

/**
 * 用户Service
 * 2020年5月11日 22:14:51
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 获取用户详情
     * @param uid
     * @return
     */
    SysUser getDetail(Long uid);

    /**
     * 验证openid是否已存在
     * @param openid
     * @return
     */
    boolean checkOpenid(String openid);

    /**
     * 通过openid获取用户详情
     * @param openid
     * @return
     */
    SysUser getByOpenid(String openid);

    /**
     * 保存用户
     * @param sysUser
     * @return
     */
    Result saveUser(SysUser sysUser);
}
