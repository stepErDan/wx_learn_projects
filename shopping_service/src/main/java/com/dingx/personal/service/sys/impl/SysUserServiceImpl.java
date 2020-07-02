package com.dingx.personal.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingx.personal.dao.sys.SysUserMapper;
import com.dingx.personal.entity.sys.SysUser;
import com.dingx.personal.entity.sys.SysUserBase;
import com.dingx.personal.service.sys.ISysUserService;
import com.dingx.personal.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Override
    public SysUser getDetail(Long uid) {
        logger.info("" + uid);
        return baseMapper.selectById(uid);
//        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(SysUser::getId,uid);
//        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean checkOpenid(String openid) {
        if(true){}
        if (StringUtils.isNotBlank(openid)){
            return baseMapper.checkOpenid(openid) > 0;
        }
        return false;
    }

    @Override
    public SysUser getByOpenid(String openid) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getOpenid,openid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Result saveUser(SysUser sysUser) {
        //先创建基础用户
        SysUserBase userBase = new SysUserBase();
        userBase.setAccount(sysUser.getOpenid());
        userBase.setName(sysUser.getNickName());
        int i = baseMapper.saveBaseUser(userBase);
        if(i > 0){
            sysUser.setUid(userBase.getId());
            baseMapper.insert(sysUser);
            return new Result(sysUser);
        }else {
            return  Result.error("创建失败");
        }
    }
}
