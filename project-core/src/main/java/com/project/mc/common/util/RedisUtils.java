/*
 * Copyright (c) 2017 上海红星美凯龙悦家互联网科技有限公司
 */
package com.project.mc.common.util;

import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaolong.wang on 2017/1/9.
 */
public class RedisUtils {
    public static <K,V> Map<byte[],byte[]> serialize(Map<K,V> from, RedisSerializer<K> keySerializer, RedisSerializer<V> valueSerializer){
        if (from == null) {
            return null;
        }

        LinkedHashMap<byte[],byte[]> map = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : from.entrySet()) {
            byte[] key = keySerializer != null ? keySerializer.serialize(entry.getKey()) : (byte[]) entry.getKey();
            byte[] value = valueSerializer != null ? valueSerializer.serialize(entry.getValue()) : (byte[]) entry.getValue();
            map.put(key, value);
        }
        return map;
    }
}
