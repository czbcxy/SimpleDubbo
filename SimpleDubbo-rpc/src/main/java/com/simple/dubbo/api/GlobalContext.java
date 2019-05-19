package com.simple.dubbo.api;

import java.util.Map;

/**
 * 用于存储全局数据，在多个组件间共享信息
 *
 * @author chengzhengzheng
 * @date 2019-05-17
 */
public interface GlobalContext {
    /**
     * 读取数据
     *
     * @param dataSetKey
     * @return
     */
    Map<String, Object> get(String dataSetKey);

    /**
     * 获取数据
     *
     * @param dataSetKey
     * @param key
     * @param <T>
     * @return
     */
    <T> T get(String dataSetKey, String key);

    /**
     * 存储数据
     *
     * @param dataSetKey
     * @param key
     * @param value
     */
    void put(String dataSetKey, String key, Object value);

    /**
     * 删除数据
     *
     * @param dataSetKey
     * @param key
     */
    void remove(String dataSetKey, String key);

    /**
     * 是否存在某一key检测
     *
     * @param dataSetKey
     * @param key
     * @return
     */
    boolean exist(String dataSetKey, String key);


}
