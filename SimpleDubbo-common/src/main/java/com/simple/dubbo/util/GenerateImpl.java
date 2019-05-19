package com.simple.dubbo.util;

/**
 * @author chengzhengzheng
 * @date 2019-05-18
 */
public class GenerateImpl<T> implements Generate<T> {

    @Override
    public T method1(T t) {
        return null;
    }

    private class GenerateImpl2 implements Generate<T> {

        @Override
        public T method1(T t) {
            return null;
        }
    }
}
