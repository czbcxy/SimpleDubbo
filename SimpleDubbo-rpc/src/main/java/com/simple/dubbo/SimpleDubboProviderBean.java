package com.simple.dubbo;

import org.springframework.beans.factory.InitializingBean;

/**
 *
 * 服务提供者配置实例
 *
 * TODO：设想 在spring中的使用方法如下、利用spirng容器实例化SimpleDubboProviderBean对象
 * <pre>
 *   &lt;bean id="HelloWorldService" class="com.simple.dubbo.service.HelloWorldServiceImpl" />
 *   &lt;bean class="com.simple.dubbo.service.SimpleDubboProviderBean">
 *   &lt;property name="serviceUri" value="/service/helloworld" />
 *   &lt;property name="interface" value="com.simple.dubbo.service.HelloWorldService" />
 *   &lt;property name="instance" ref="HelloWorldService" />
 *   &lt;/bean>
 *  </pre>
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 **/
public class SimpleDubboProviderBean implements InitializingBean {


    /**
     * 服务元信息
     */
    private ServiceMetadata serviceMetadata;
    /**
     * 服务实例对象、例如HelloWorldServiceImpl
     */
    private Object serviceInstance;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public void init(){
        //1.服务元信息 ServiceMetadata中协议的设置
        //2.调用服务工程类ServiceProviderFactory通过netty启动服务
        new ServiceProviderFactory(8080);
        //3.调用serviceProviderFactory的registerService注册服务
    }

    /**
     * 对应使用方法中的  property name="serviceUri" value="/service/helloworld"
     * @param serviceUrl
     */
    public void setServiceUrl(String serviceUrl){
        //TODO
    }

    /**
     * 对应使用方法中的  property name="interface" value="com.simple.dubbo.service.HelloWorldService"
     * @param interfaceName
     */
    public void setInterface(String interfaceName){
        //TODO
    }

    /**
     * 对应使用方法中的 property name="instance" ref="HelloWorldService"
     * @param instance
     */
    public void setInstance(Object instance){
        //TODO
    }

}
