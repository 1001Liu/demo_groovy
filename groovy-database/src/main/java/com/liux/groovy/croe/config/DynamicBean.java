package com.liux.groovy.croe.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 15:19
 * @description :配置bean属性
 */
public class DynamicBean {
    /**
     * 存储属性的map
     */
    private final Map<String, String> properties = new HashMap<String, String>();

    /**
     * 添加属性
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        properties.put(key, value);
    }

    /**
     * 遍历属性
     *
     * @return
     */
    public Iterator<String> keyIterator() {
        return properties.keySet().iterator();
    }

    /**
     * 返回属性值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return properties.get(key);
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        StringBuilder retValue = new StringBuilder("DynamicBean[");
        retValue.append("properties=").append(this.properties).append(']');
        return retValue.toString();
    }

}
