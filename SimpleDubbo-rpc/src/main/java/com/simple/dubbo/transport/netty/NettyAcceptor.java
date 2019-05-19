package com.simple.dubbo.transport.netty;

import com.simple.dubbo.api.Constants;
import com.simple.dubbo.api.service.Processor;
import com.simple.dubbo.transport.Acceptor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author chengzhengzheng
 * @date 2019-05-19
 */
public abstract class NettyAcceptor implements Acceptor {
    private final Protocol protocol;
    private final SocketAddress socketAddress;

    private final int nBosses;
    private final int nWorks;


    private ServerBootstrap bootstrap;
    private EventLoopGroup bossGoup;
    private EventLoopGroup workGroup;


    private Processor processor;

    public NettyAcceptor(Protocol protocol, SocketAddress socketAddress) {
        this(protocol, socketAddress, Constants.AVAILABLE_PROCESSORS << 2);
    }

    public NettyAcceptor(int port) {
        this(Protocol.TCP, new InetSocketAddress(port));
    }

    public NettyAcceptor(Protocol protocol, SocketAddress socketAddress, int nWorks) {
        this(protocol, socketAddress, 1, nWorks);
    }

    public NettyAcceptor(Protocol protocol, SocketAddress socketAddress, int nBosses, int nWorks) {
        this.protocol = protocol;
        this.socketAddress = socketAddress;
        this.nBosses = nBosses;
        this.nWorks = nWorks;
    }


}
