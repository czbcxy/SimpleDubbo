package com.simple.dubbo.log.log4j;

import com.simple.dubbo.log.AbstractLogger;
import com.simple.dubbo.log.level.Level;

/**
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public class Log4jLogger extends AbstractLogger {
    private org.apache.log4j.Logger logger;

    public Log4jLogger(org.apache.log4j.Logger logger) {
        this.logger = logger;
    }

    public Log4jLogger(String name) {
        this(org.apache.log4j.Logger.getLogger(name));
    }

    public Log4jLogger(Class<?> clazz) {
        this(org.apache.log4j.Logger.getLogger(clazz));
    }


    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public void debug(String format, Object... arguments) {

    }

    @Override
    public void debug(Throwable t, String format, Object... arguments) {

    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public void error(String format, Object... arguments) {

    }

    @Override
    public void error(Throwable t, String format, Object... arguments) {

    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public void info(String format, Object... arguments) {

    }

    @Override
    public void info(Throwable t, String format, Object... arguments) {

    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void trace(String format, Object... arguments) {

    }

    @Override
    public void trace(Throwable t, String format, Object... arguments) {

    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void warn(String format, Object... arguments) {

    }

    @Override
    public void warn(Throwable t, String format, Object... arguments) {

    }

    @Override
    public void log(Level level, String format, Object... arguments) {
        logger.info("helo");
    }

    @Override
    public void log(Level level, Throwable t, String format, Object... arguments) {

    }
}
