package com.simple.dubbo.log;

/**
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public class testLogger {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("heartbeat");
        logger.debug("hello");
    }
}
