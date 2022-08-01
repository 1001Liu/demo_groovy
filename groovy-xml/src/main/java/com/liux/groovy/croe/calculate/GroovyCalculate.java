package com.liux.groovy.croe.calculate;

import com.liux.groovy.croe.entity.request.GroovyRequest;
import com.liux.groovy.croe.entity.response.GroovyResponse;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 13:59
 * @description :统一实现接口
 */
public interface GroovyCalculate {
    /**
     * 调用接口
     * @param request 入参
     * @return 返回值
     */
    GroovyResponse parse(GroovyRequest request);
}
