package com.simple.dubbo.transport;

import com.simple.dubbo.Transporter;
import com.simple.dubbo.api.service.Processor;

/**
 * Server acceptor.
 * <p>
 * 网络层acceptor
 *
 * @author chengzhengzheng
 * @date 2019-05-19
 */
public interface Acceptor extends Transporter {

    int boundPort();

    Processor processor();

    void withProcessor(Processor processor);

    void start() throws InterruptedException;

    void start(boolean sync) throws InterruptedException;

    void shutdownGraceully();

}
