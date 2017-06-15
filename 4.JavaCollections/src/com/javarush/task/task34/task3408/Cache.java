package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V value = cache.get(key);
        if (value == null) {
            value = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, value);
        }
        return value;
    }

    public boolean put(V obj) {
        try {
            Method getKey = obj.getClass().getDeclaredMethod("getKey");
            getKey.setAccessible(true);
            K key = (K) getKey.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
