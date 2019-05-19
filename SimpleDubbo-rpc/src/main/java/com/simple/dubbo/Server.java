package com.simple.dubbo;

import com.simple.dubbo.transport.Acceptor;

/**
 * @author chengzhengzheng
 * @date 2019-05-18
 */
public interface Server {

    /**
     * 设置网络层acceptor.
     */
    void withAcceptor(Acceptor acceptor);
}
