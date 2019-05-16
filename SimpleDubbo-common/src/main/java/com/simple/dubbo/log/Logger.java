package com.simple.dubbo.log;

import com.simple.dubbo.log.level.*;

/**
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public interface Logger extends DebugLog, ErrorLog, InfoLog, TraceLog, WarnLog {

    /**
     * 是否开启指定日志
     *
     * @param level 日志级别
     * @return 是否开启指定级别
     */
    boolean isEnabled(Level level);

    /**
     * 打印指定级别的日志
     *
     * @param level     级别
     * @param format    消息模板
     * @param arguments 参数
     */
    void log(Level level, String format, Object... arguments);

    /**
     * 打印 指定级别的日志
     *
     * @param level     级别
     * @param t         错误对象
     * @param format    消息模板
     * @param arguments 参数
     */
    void log(Level level, Throwable t, String format, Object... arguments);

}
