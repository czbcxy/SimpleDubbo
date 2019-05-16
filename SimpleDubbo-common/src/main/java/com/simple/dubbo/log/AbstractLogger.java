package com.simple.dubbo.log;

import com.simple.dubbo.exception.ExceptionUtil;
import com.simple.dubbo.log.level.Level;

/**
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public abstract class AbstractLogger implements Logger {

    @Override
    public boolean isEnabled(Level level) {
        switch (level) {
            case TRACE:
                return isTraceEnabled();
            case DEBUG:
                return isDebugEnabled();
            case INFO:
                return isInfoEnabled();
            case WARN:
                return isWarnEnabled();
            case ERROR:
                return isErrorEnabled();
            default:
                throw new Error(String.format("Can not identify level: %s", level));
        }
    }


    @Override
    public void trace(Throwable t) {
        this.trace(t, ExceptionUtil.getSimpleMessage(t));
    }

    @Override
    public void debug(Throwable t) {
        this.debug(t, ExceptionUtil.getSimpleMessage(t));
    }

    @Override
    public void info(Throwable t) {
        this.info(t, ExceptionUtil.getSimpleMessage(t));
    }

    @Override
    public void warn(Throwable t) {
        this.warn(t, ExceptionUtil.getSimpleMessage(t));
    }

    @Override
    public void error(Throwable t) {
        this.error(t, ExceptionUtil.getSimpleMessage(t));
    }

}
