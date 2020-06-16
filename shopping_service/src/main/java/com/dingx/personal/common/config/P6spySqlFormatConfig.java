package com.dingx.personal.common.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义 p6spy sql输出格式
 *
 */
public class P6spySqlFormatConfig implements MessageFormattingStrategy {

    /**
     * 过滤掉定时任务的 SQL
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? formatLocalDateTime(LocalDateTime.now())
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : "";
    }


    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }
}