package com.dingx.personal.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingx.personal.dao.sys.SysUserTempMapper;
import com.dingx.personal.entity.sys.SysUserTemp;
import com.dingx.personal.service.sys.ISysUserTempService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysUserTempServiceImpl extends ServiceImpl<SysUserTempMapper, SysUserTemp> implements ISysUserTempService {

    private final static Logger logger = LoggerFactory.getLogger(SysUserTempServiceImpl.class);

    @Override
    public int updateByOpenid(SysUserTemp userTemp) {
        return baseMapper.update(userTemp,new QueryWrapper<SysUserTemp>().lambda().eq(SysUserTemp::getOpenid,userTemp.getOpenid()));
    }

    @Override
    public SysUserTemp getByToken(String token) {
        QueryWrapper<SysUserTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserTemp::getToken,token);
        return baseMapper.selectOne(queryWrapper);
    }
}
