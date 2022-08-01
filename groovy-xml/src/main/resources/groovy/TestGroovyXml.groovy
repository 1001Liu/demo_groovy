package groovy

import com.liux.groovy.croe.calculate.GroovyCalculate
import com.liux.groovy.croe.entity.request.GroovyRequest
import com.liux.groovy.croe.entity.response.GroovyResponse


class TestGroovyXml implements GroovyCalculate {
    @Override
    GroovyResponse parse(GroovyRequest request) {
        request.println()
        return GroovyResponse.builder()
                .beanName(request.getBeanName())
                .groovyName(request.groovyName)
                .data("测试xml配置bean")
                .build()
    }
}
