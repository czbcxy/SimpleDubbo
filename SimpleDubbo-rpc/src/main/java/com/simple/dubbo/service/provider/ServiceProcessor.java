package com.simple.dubbo.service.provider;

import com.simple.dubbo.api.Request;
import com.simple.dubbo.api.Response;
import com.simple.dubbo.service.ServiceMetadata;

/**
 * 服务处理器：维护了业务服务的服务实例。所有的服务请求都最终交由这里处理。
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 */
public class ServiceProcessor extends AbstractProcessor {
    /**
     *
     * @param serviceMetadata
     * @param serviceInstance
     */
    public ServiceProcessor(ServiceMetadata serviceMetadata, Object serviceInstance) {
        super(serviceMetadata,serviceInstance);
        //TODO
    }

    @Override
    protected Response doHandleRequest(Request request) {
        //反序列化请求参数
        //调用服务
//        return invoke(method, parameters, request, response);
        return null;
    }
}
