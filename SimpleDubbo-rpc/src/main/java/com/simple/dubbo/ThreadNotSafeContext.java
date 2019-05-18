package com.simple.dubbo;

import com.simple.dubbo.api.GlobalContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author chengzhengzheng
 * @date 2019-05-17
 */
public class ThreadNotSafeContext implements GlobalContext {

    private ThreadNotSafeContext() {

    }

    private static class SingleHolder {
        private static ThreadNotSafeContext INSTANCE = new ThreadNotSafeContext();
    }

    public static ThreadNotSafeContext getInstance() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 数据域、模拟redis的hash
     */
    private ConcurrentMap<String, Map<String, Object>> datas = new ConcurrentHashMap<>();

    @Override
    public Map<String, Object> get(String dataSetKey) {
        if (!datas.containsKey(dataSetKey)) {
            return null;
        }
        return Collections.unmodifiableMap(datas.get(datas));
    }

    @Override
    public <T> T get(String dataSetKey, String key) {
        if (!datas.containsKey(dataSetKey)) {
            return null;
        }
        return (T) datas.get(dataSetKey).get(key);
    }

    @Override
    public void put(String dataSetKey, String key, Object value) {
        if (!datas.containsKey(dataSetKey)) {
            Map<String, Object> map = new HashMap<>();
            datas.put(dataSetKey, map);
        }
        Map<String, Object> map = datas.get(dataSetKey);
        synchronized (map) {
            map.put(key, value);
        }
    }

    @Override
    public void remove(String dataSetKey, String key) {
        if (datas.containsKey(dataSetKey)) {
            Map<String, Object> map = datas.get(dataSetKey);
            synchronized (map) {
                map.remove(key);
            }
        }
    }

    @Override
    public boolean exist(String dataSetKey,String key) {
        if(!datas.containsKey(dataSetKey)){
            return false;
        }
        return datas.get(dataSetKey).containsKey(key);
    }
}
