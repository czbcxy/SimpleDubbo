package com.simple.dubbo.log;

import com.simple.dubbo.log.log4j.Log4jFactory;
import com.simple.dubbo.log.log4j2.Log4j2Factory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 日志工厂类
 *
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public abstract class LoggerFactory {

    protected String name;
    /**
     * 具体的logger缓存
     */
    private static final ConcurrentMap<Object, Logger> loggerCache = new ConcurrentHashMap<>();
    /**
     * 当前正在使用的LoggerFactory
     */
    private static volatile LoggerFactory currentLoggerFactory;

    static {
        init();
    }

    private static void init() {
        final String loggerName = System.getProperty("simple.dubbo.logger", "");
        switch (loggerName) {
            case "log4j":
                setCurrentLoggerFactory(Log4jFactory.class);
                break;
            case "log4j2":
                setCurrentLoggerFactory(Log4j2Factory.class);
                break;
            default:
                setCurrentLoggerFactory(Log4jFactory.class);
                break;
        }
    }

    public LoggerFactory(String name) {
        this.name = name;
    }

    /**
     * @param loggerFactory
     */
    public static LoggerFactory setCurrentLoggerFactory(Class<? extends LoggerFactory> loggerFactory) {
        try {
            return set(loggerFactory.newInstance());
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not instance LoggerFactory class!");
        }
    }

    private static LoggerFactory set(LoggerFactory newInstance) {
        LoggerFactory.currentLoggerFactory = newInstance;
        return currentLoggerFactory;
    }


    /**
     * 日志提供者
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings("all")
    public Logger getLogger(Class<?> clazz) {
        Logger logger = loggerCache.get(clazz);
        if (null == logger) {
            logger = getCurrentLoggerFactory().createLogger(clazz);
            loggerCache.put(clazz, logger);
        }
        return logger;
    }

    @SuppressWarnings("all")
    public Logger getLogger(String name) {
        Logger logger = loggerCache.get(name);
        if (null == logger) {
            logger = createLogger(name);
            loggerCache.put(name, logger);
        }
        return logger;
    }

    public abstract Logger createLogger(String key);

    public abstract Logger createLogger(Class<?> clazz);

    /**
     * 获取当前正在使用的loggerFactory
     *
     * @return
     */
    public static LoggerFactory getCurrentLoggerFactory() {
        assert currentLoggerFactory != null;
        return currentLoggerFactory;
    }
}
