package com.simple.dubbo.log.log4j2;

import com.simple.dubbo.log.AbstractLogger;
import org.apache.logging.log4j.LogManager;

/**
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public class Log4j2Logger extends AbstractLogger {

    private final org.apache.logging.log4j.Logger logger;

    public Log4j2Logger(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

    public Log4j2Logger(Class<?> clazz) {
        this(LogManager.getLogger(clazz));
    }

    public Log4j2Logger(String name) {
        this(LogManager.getLogger(name));
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
}
