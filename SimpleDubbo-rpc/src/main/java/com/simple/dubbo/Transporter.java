package com.simple.dubbo;

/**
 * @author chengzhengzheng
 * @date 2019-05-19
 */
public interface Transporter {

    Protocol protocol();

    enum Protocol {
        TCP,
        REDIS
    }
}
