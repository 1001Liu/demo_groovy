package com.liux.groovy.croe.constant;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 14:19
 * @description :groovy常量
 */
public class GroovyConstant {
    /**
     * GROOVY 脚本配置标签
     */
    public static final String SPRING_TAG = "lang:groovy";

    /**
     * GROOVY 脚本在配置中的前缀
     */
    public static final String SCRIPT_SOURCE_PREFIX = "database:";


    /**
     * 配置文件开头
     */
    public static final String BEANS_FILE_CONTENT = "<beans xmlns='http://www.springframework.org/schema/beans'\n" +
            "       xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" +
            "       xmlns:lang='http://www.springframework.org/schema/lang'\n" +
            "       xsi:schemaLocation='http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
            "       http://www.springframework.org/schema/lang\n" +
            "       http://www.springframework.org/schema/lang/spring-lang.xsd'>" +"</beans>";
}
