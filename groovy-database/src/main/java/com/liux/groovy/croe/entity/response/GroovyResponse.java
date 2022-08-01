package com.liux.groovy.croe.entity.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 14:13
 * @description : 结果返回
 */
@Data
@Builder
public class GroovyResponse {
    private String groovyName;
    private String beanName;
    private Object data;
}
