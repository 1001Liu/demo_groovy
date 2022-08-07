package com.liux.groovy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liux.groovy.entity.GroovyScript;
import com.liux.groovy.service.IGroovyScriptService;
import com.liux.groovy.utils.SpringContextUtils;
import groovy.lang.GroovyClassLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author 11512
 */
@SpringBootApplication
public class GroovyDatabaseApplication {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        SpringApplication.run(GroovyDatabaseApplication.class, args);
        IGroovyScriptService scriptService = SpringContextUtils.getBean(IGroovyScriptService.class);
        List<GroovyScript> list = scriptService.list(new LambdaQueryWrapper<GroovyScript>()
                .eq(GroovyScript::getBeanName, "test02")
                .eq(GroovyScript::getIsDelete, 0));
        GroovyScript groovyScript = list.get(0);
        Class testCa = new GroovyClassLoader().parseClass(groovyScript.getGroovyContent());
        SpringContextUtils.autowireBean(testCa,groovyScript.getBeanName());
    }

}
