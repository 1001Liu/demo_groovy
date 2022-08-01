package com.liux.groovy.croe.scrpit

import com.liux.groovy.croe.calculate.GroovyCalculate
import com.liux.groovy.croe.entity.request.GroovyRequest
import com.liux.groovy.croe.entity.response.GroovyResponse


class Test01 implements GroovyCalculate {
    @Override
    GroovyResponse parse(GroovyRequest request) {
        request.println()
        return GroovyResponse.builder()
                .beanName(request.getBeanName())
                .groovyName(request.groovyName)
                .data(1)
                .build()
    }
}
