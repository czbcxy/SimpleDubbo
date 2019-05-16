package com.simple.dubbo.exception;

/**
 * @author chengzhengzheng
 * @date 2019-05-16
 **/
public class ExceptionUtil {

    private static final String NULL = "null";
    /**
     * 获得消息，调用异常类的getMessage方法
     *
     * @param e 异常
     * @return 消息
     */
    public static String getSimpleMessage(Throwable e) {
        return (null == e) ? NULL : e.getMessage();
    }
}
