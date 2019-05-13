package com.simple.dubbo;

/**
 * 负责管理服务的发布和订阅流程。所有与ConfigCenter交互的功能都由该组件进行处理。
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 **/
public interface RegisterManagerService {
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


    //TODO 其他功能
}
