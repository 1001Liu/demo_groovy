package com.liux.groovy.croe.calculate.impl;

import com.liux.groovy.croe.entity.request.GroovyRequest;
import com.liux.groovy.croe.entity.response.GroovyResponse;
import com.liux.groovy.croe.calculate.GroovyCalculate;
import com.liux.groovy.croe.calculate.GroovyParser;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 13:59
 * @description :
 */
@Component
public class GroovyParserImpl implements GroovyParser {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public GroovyResponse parse(GroovyRequest request) {
        GroovyCalculate calculate = (GroovyCalculate) applicationContext.getBean(request.getBeanName());
        return calculate.parse(request);
    }
}
