package com.simple.dubbo.log.log4j;


import com.simple.dubbo.log.Logger;
import com.simple.dubbo.log.LoggerFactory;

/**
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public class Log4jFactory extends LoggerFactory {


    public Log4jFactory() {
        super("log4j");

    }

    @Override
    public Logger createLogger(String key) {
        return new Log4jLogger(name);
    }

    @Override
    public Logger createLogger(Class<?> clazz) {
        return new Log4jLogger(clazz);
    }
}
