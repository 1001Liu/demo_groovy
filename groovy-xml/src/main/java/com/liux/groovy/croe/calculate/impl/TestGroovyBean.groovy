package com.liux.groovy.croe.calculate.impl

import com.liux.groovy.croe.calculate.GroovyCalculate
import com.liux.groovy.croe.entity.request.GroovyRequest
import com.liux.groovy.croe.entity.response.GroovyResponse
import org.springframework.stereotype.Component


@Component
class TestGroovyBean implements GroovyCalculate {
    @Override
    GroovyResponse parse(GroovyRequest request) {
        println(request)
        return GroovyResponse.builder()
                .beanName(request.getBeanName())
                .groovyName(request.groovyName)
                .data(request.param)
                .build()
    }
}
