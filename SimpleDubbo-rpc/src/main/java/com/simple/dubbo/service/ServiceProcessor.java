package com.simple.dubbo.service;

/**
 * 服务处理器：维护了业务服务的服务实例。所有的服务请求都最终交由这里处理。
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 */
public class ServiceProcessor extends AbstraceProcessor {
    @Override
    protected Response doHandleRequest(Request request) {
        //反序列化请求参数
        //调用服务
//        return invoke(method, parameters, request, response);
        return null;
    }
}
