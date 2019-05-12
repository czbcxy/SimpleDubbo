package com.simple.dubbo.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author chengzhengzheng
 * @date 2019-05-12
 */
public class NettyTcpServer extends AbstractServer {

    public NettyTcpServer(int tcpPort, ServiceProviderFactory serviceProviderFactory) {
        super(tcpPort, "tcp");


    }

    private ServerBootstrap bootstrap;

    @Override
    public void start() {
        //netty的经典代码

        bootstrap = new ServerBootstrap();

        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast("encode", null);
                        ch.pipeline().addLast("decode", null);
                        ch.pipeline().addLast(new TcpServerHandler(serviceProviderFactory));
                    }
                });


    }

    @Override
    public void stop() {

    }
}
