package com.simple.dubbo.api.registry;

import com.simple.dubbo.service.ServiceMetadata;

/**
 * 负责管理服务的发布和订阅流程。所有与ConfigCenter交互的功能都由该组件进行处理。
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 **/
public interface RegisterService {
    /**
     * 发布服务
     *
     * @param data
     */
    void publish(ServiceMetadata data);

    /**
     * 订阅服务
     *
     * @param data
     */
    void subscribe(ServiceMetadata data);


    enum RegistryType {
        DEFAULT("default"),
        ZOOKEEPER("zookeeper");

        String value;

        RegistryType(String value) {
            this.value = value;
        }

        RegistryType enumOf(String value){
            for(RegistryType type : values()){
                if(type.value.equals(value)){
                    return type;
                }
            }
            return null;
        }
    }
}
