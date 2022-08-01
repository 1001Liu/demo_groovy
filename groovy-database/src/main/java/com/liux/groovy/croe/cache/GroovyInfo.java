package com.liux.groovy.croe.cache;

import lombok.Builder;
import lombok.Data;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 14:44
 * @description :groovy脚本
 */
@Data
@Builder
public class GroovyInfo {

    /**
     * 脚本名称
     */
    private String groovyName;

    /**
     * 脚本名称
     */
    private String beanName;

    /**
     * 脚本路径
     */
    private String path;

    /**
     * 脚本内容
     */
    private String groovyContent;
}
