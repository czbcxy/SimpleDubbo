package com.simple.dubbo.spring;

import com.simple.dubbo.Server;
import com.simple.dubbo.api.registry.RegisterService;
import com.simple.dubbo.service.ServiceMetadata;
import com.simple.dubbo.transport.Acceptor;
import com.simple.dubbo.util.SystemPropertyUtil;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.atomic.AtomicBoolean;

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

    private static final AtomicBoolean inited = new AtomicBoolean(false);
    /**
     * 服务元信息
     */
    private ServiceMetadata serviceMetadata;
    /**
     * 服务实例对象、例如HelloWorldServiceImpl
     */
    private Object serviceInstance;

    /**
     * 全局Server
     */
    private Server server;

    /**
     * 接收IO请求者
     */
    private Acceptor acceptor;

    private RegisterService.RegistryType registryType;


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public void init(){
        if(inited.compareAndSet(false,true)){
            initServer();
        }
        //注册服务
    }

    private void initServer() {
        server = new DefaultServer(registryType);
        if(acceptor == null){
            acceptor = createDefaultAcceptor();
        }
        server.withAcceptor(acceptor);
    }

    private Acceptor createDefaultAcceptor() {
        Acceptor defaultAcceptor = null;
        try {
            String acceptorName = SystemPropertyUtil.get("simple.dubbo.default.acceptor", "com.simple.dubbo.transport.netty.NettyTcpAcceptor");
            Class<?> className = Class.forName(acceptorName);
            defaultAcceptor = (Acceptor) className.newInstance();
        }catch (Exception e){

        }
        return defaultAcceptor;
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
        serviceMetadata.setServiceName(interfaceName);
    }

    /**
     * 对应使用方法中的 property name="instance" ref="HelloWorldService"
     * @param instance
     */
    public void setInstance(Object instance){
        //TODO
    }

}
