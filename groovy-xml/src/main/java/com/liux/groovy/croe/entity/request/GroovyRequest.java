package com.liux.groovy.croe.entity.request;

import lombok.Data;

import java.util.Map;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 14:12
 * @description :脚本请求参数
 */
@Data
public class GroovyRequest {
    private String groovyName;
    private String beanName;
    private Map<String, String> param;
}
