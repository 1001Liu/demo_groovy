package com.liux.groovy.croe.calculate;

import com.liux.groovy.croe.entity.request.GroovyRequest;
import com.liux.groovy.croe.entity.response.GroovyResponse;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 13:58
 * @description :对外调用接口
 */
public interface GroovyParser {

    /**
     * 调用接口
     * @param request 入参
     * @return 返回值
     */
    GroovyResponse parse(GroovyRequest request);
}
