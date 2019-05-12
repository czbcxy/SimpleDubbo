package com.simple.dubbo.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author chengzhengzheng
 * @date 2019-05-12
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    private final ServiceProviderFactory serviceProviderFactory;

    public TcpServerHandler(ServiceProviderFactory serviceProviderFactory) {
        this.serviceProviderFactory = serviceProviderFactory;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        //1.查找服务处理器 Processor
        //2.同步处理请求  Processor的handleRequest方法
        //3.写入channel当中、flush
    }


}
