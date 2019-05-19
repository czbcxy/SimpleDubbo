package com.simple.dubbo.service.provider;

import com.simple.dubbo.ThreadNotSafeContext;
import com.simple.dubbo.api.Constants;
import com.simple.dubbo.api.GlobalContext;
import com.simple.dubbo.api.registry.RegisterManagerService;
import com.simple.dubbo.api.service.Processor;
import com.simple.dubbo.service.NettyTcpServer;
import com.simple.dubbo.service.ServiceFactory;
import com.simple.dubbo.service.ServiceMetadata;
import com.simple.dubbo.service.registry.RegisterManagerComponent;

/**
 * 服务提供端负责初始化和管理所有的服务元数据、服务实例的工厂
 * 工厂模板
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 **/
public class ServiceProviderFactory extends ServiceFactory {

    private RegisterManagerService registerManagerService = RegisterManagerComponent.getInstance();

    public ServiceProviderFactory(int tcpPort) {
        //1.监控组件初始化
        //2.启动服务
        new NettyTcpServer(tcpPort,this).start();

    }

    /**
     * 注册服务实例
     * @param serviceMetadata
     * @param serviceInstance
     */
    public void registerService(ServiceMetadata serviceMetadata, Object serviceInstance){
        final String serviceUrl = serviceMetadata.getServiceUri();
        //1. 注册服务线程池。 (每一个服务url都有各自对应的线程池、这样单个服务qps暴增、不会引起整个集群不可用  TODO 有待商埠)
        //2. 登记服务 (保存服务--->服务处理器的映射  参考Processor)
        Processor processor = new ServiceProcessor(serviceMetadata,serviceInstance);
        //3.保存服务全局信息
        GlobalContext context = ThreadNotSafeContext.getInstance();
        context.put(Constants.KEY_STORE_PROVIDER_METADATAS, serviceUrl, serviceMetadata);
        context.put(Constants.KEY_STORE_PROVIDER_SERVICES,serviceUrl,processor);
        //4. 发布服务
        registerManagerService.publish(serviceMetadata);

    }
}
