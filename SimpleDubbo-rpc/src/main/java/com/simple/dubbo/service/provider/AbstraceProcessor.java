package com.simple.dubbo.service.provider;

import com.simple.dubbo.Processor;
import com.simple.dubbo.api.Request;
import com.simple.dubbo.api.Response;

import java.lang.reflect.Method;

/**
 * 抽象的服务处理器
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 */
public abstract class AbstraceProcessor implements Processor {


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
