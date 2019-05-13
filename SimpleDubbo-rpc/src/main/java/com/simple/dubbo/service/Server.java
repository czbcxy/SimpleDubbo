package com.simple.dubbo.service;

/**
 * 服务实例抽象:主要提供start和stop方法
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 */
public interface Server {
    /**
     * Start the server
     */
    void start();

    /**
     * Stop the server
     */
    void stop();


}
