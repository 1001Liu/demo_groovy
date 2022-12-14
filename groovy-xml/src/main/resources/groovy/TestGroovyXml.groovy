package groovy

import com.liux.groovy.croe.calculate.GroovyCalculate
import com.liux.groovy.croe.entity.request.GroovyRequest
import com.liux.groovy.croe.entity.response.GroovyResponse


class TestGroovyXml implements GroovyCalculate {
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
