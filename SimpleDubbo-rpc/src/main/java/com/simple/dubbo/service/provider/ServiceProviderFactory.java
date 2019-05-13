package com.simple.dubbo.service.provider;

import com.simple.dubbo.ServiceFactory;
import com.simple.dubbo.ServiceMetadata;
import com.simple.dubbo.service.NettyTcpServer;

/**
 * 服务提供端负责初始化和管理所有的服务元数据、服务实例的工厂
 * 工厂模板
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 **/
public class ServiceProviderFactory extends ServiceFactory {


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
        //1. 注册服务线程池。 (每一个服务url都有各自对应的线程池、这样单个服务qps暴增、不会引起整个集群不可用  TODO 有待商埠)
        //2. 登记服务 (保存服务--->服务处理器的映射  参考Processor)
        //3. 发布服务

    }
}
