package com.simple.dubbo.spring;

import com.simple.dubbo.api.registry.RegisterService;
import com.simple.dubbo.transport.Acceptor;
import com.simple.dubbo.util.DebbyServiceLoader;

/**
 * @author chengzhengzheng
 * @date 2019-05-18
 */
public class DefaultServer implements com.simple.dubbo.Server {

    private RegisterService registerService;


    public DefaultServer(RegisterService.RegistryType registryType) {
        registryType = registryType == null ? RegisterService.RegistryType.DEFAULT : registryType;
        registerService = DebbyServiceLoader.load(RegisterService.class).find(registryType.name());
    }

    @Override
    public void withAcceptor(Acceptor acceptor) {

    }
}
