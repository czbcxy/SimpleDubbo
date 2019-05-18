package com.simple.dubbo.service.provider;

import com.google.common.collect.Maps;
import com.simple.dubbo.api.Request;
import com.simple.dubbo.api.Response;
import com.simple.dubbo.api.service.Processor;
import com.simple.dubbo.service.ServiceMetadata;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 抽象的服务处理器
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 */
public abstract class AbstractProcessor implements Processor {

    private final Map<String, Method> methods = Maps.newHashMap();
    private final ServiceMetadata serviceMetadata;
    private final Object serviceInstance;
    private final String serviceUrl;

    public AbstractProcessor(ServiceMetadata serviceMetadata, Object serviceInstance) {
        this.serviceInstance = serviceInstance;
        this.serviceMetadata = serviceMetadata;
        this.serviceUrl = serviceMetadata.getServiceUri();

        Class<?> serviceInterface = serviceMetadata.getServiceInterface();
        for (Method method : serviceInterface.getDeclaredMethods()) {
            final String methodName = method.getName();
            if (methods.containsKey(methodName)) {
                //error
            }

            methods.put(methodName, method);
        }


    }

    @Override
    public Response handleRequest(Request request) {
        //处理rpc请求
        //
        try {
            return doHandleRequest(request);
        } catch (Exception e) {

        }
        return null;
    }

    protected abstract Response doHandleRequest(Request request);

    /**
     * 反射调用服务。
     */
    protected Response invoke(Method method, Object[] parameters, Request request, Response response) {
        return null;
    }
}
