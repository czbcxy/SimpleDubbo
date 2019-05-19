package com.simple.dubbo.transport.netty;

import java.net.SocketAddress;

/**
 * @author chengzhengzheng
 * @date 2019-05-19
 */
public abstract class NettyTcpAcceptor extends NettyAcceptor {


    public NettyTcpAcceptor(Protocol protocol, SocketAddress socketAddress) {
        super(protocol, socketAddress);
    }

    public NettyTcpAcceptor(Protocol protocol, SocketAddress socketAddress, int nWorks) {
        super(protocol, socketAddress, nWorks);
    }

    public NettyTcpAcceptor(Protocol protocol, SocketAddress socketAddress, int nBosses, int nWorks) {
        super(protocol, socketAddress, nBosses, nWorks);
    }
}
