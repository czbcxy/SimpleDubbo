package com.simple.dubbo.api.remote;

/**
 * 远程服务抽象
 *
 * @author chengzhengzheng
 * @date 2019-05-13
 */
public interface IServer {
    /**
     * 启动服务
     */
    void start();

    /**
     * 暂停服务
     */
    void stop();

}
