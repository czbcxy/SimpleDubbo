package com.simple.dubbo.log.log4j2;


import com.simple.dubbo.log.Logger;
import com.simple.dubbo.log.LoggerFactory;

/**
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public class Log4j2Factory extends LoggerFactory {

    public Log4j2Factory() {
        super("log4j2");
    }

    @Override
    public Logger createLogger(String name) {
        return new Log4j2Logger(name);
    }

    @Override
    public Logger createLogger(Class<?> clazz) {
        return new Log4j2Logger(clazz);
    }
}
