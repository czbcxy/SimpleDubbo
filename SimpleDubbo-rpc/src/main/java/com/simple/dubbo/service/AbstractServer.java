package com.simple.dubbo.service;

import com.simple.dubbo.api.remote.IServer;
import com.simple.dubbo.service.provider.ServiceProviderFactory;

/**
 * @author chengzhengzheng
 * @date 2019-05-12
 */
public abstract class AbstractServer implements IServer {
    protected final int port;
    private final String protocol;
    //服务实例工厂
    protected ServiceProviderFactory serviceProviderFactory;

    public AbstractServer(int port, String protocol) {
        this.port = port;
        this.protocol = protocol;
    }



}
