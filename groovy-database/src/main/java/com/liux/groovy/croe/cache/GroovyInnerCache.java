package com.liux.groovy.croe.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 14:43
 * @description :groovy相关缓存
 */
public class GroovyInnerCache {

    private static Map<String, GroovyInfo> GROOVY_NAME_MAP = new ConcurrentHashMap<>();

    private static Map<String, GroovyInfo> BEAN_NAME_MAP = new ConcurrentHashMap<>();

    public static void put2groovyMap(List<GroovyInfo> list) {
        if (!GROOVY_NAME_MAP.isEmpty()) {
            GROOVY_NAME_MAP.clear();
        }
        GROOVY_NAME_MAP = list.stream().collect(Collectors.toConcurrentMap(GroovyInfo::getGroovyName, Function.identity()));
    }

    public static void put2beanMap(List<GroovyInfo> list) {
        if (!BEAN_NAME_MAP.isEmpty()) {
            BEAN_NAME_MAP.clear();
        }
        BEAN_NAME_MAP = list.stream().collect(Collectors.toConcurrentMap(GroovyInfo::getBeanName, Function.identity()));
    }

    public static GroovyInfo getByGroovyName(String groovyName) {
        return GROOVY_NAME_MAP.get(groovyName);
    }

    public static GroovyInfo getByBeanName(String beanName) {
        return BEAN_NAME_MAP.get(beanName);
    }
}
