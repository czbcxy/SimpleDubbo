package com.simple.dubbo.service;

import java.util.Properties;

/**
 * 务元数据：服务配置文件将最终被转换为服务元数据实例，这里维护了：
 * <ul>
 *      <li>服务的serviceUri、名称及相关属性
 *      <li>对服务提供端：服务被对外发布的协议，及相关属性
 *      <li>对服务消费端：服务调用协议，及相关属性
 * </ul>
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 **/
public class ServiceMetadata {

    private String serviceUri;
    private String serviceName;
    private String instanceName;
    private Class<?> serviceInterface;
    private Properties properties = new Properties();




    public String getServiceUri() {
        return null;
    }

    public Class<?> getServiceInterface() {
        if (null == serviceInterface) {
            try {
                serviceInterface = Class.forName(serviceName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return serviceInterface;
    }

    public void setServiceName(String interfaceName) {
        this.instanceName = interfaceName;
    }
}
