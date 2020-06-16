package com.dingx.personal.common.util;


import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义ID生成器
 * 仅作为示范
 *
 * @author nieqiuqiu 2019/11/30
 */
@Slf4j
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Long nextId(Object entity) {
        final long id = SnowflakeIdWorker.generateId();
        return id;
    }


}