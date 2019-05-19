package com.simple.dubbo.api.service;

import com.simple.dubbo.api.Request;
import com.simple.dubbo.api.Response;

/**
 * 服务处理器。
 *
 * @author chengzhengzheng
 * @date 2019-05-12
 **/
public interface Processor {

    /**
     * 处理服务、暂定
     * @param request
     * @return
     */
    Response handleRequest(Request request);
}
