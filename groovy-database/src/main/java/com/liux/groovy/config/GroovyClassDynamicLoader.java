package com.liux.groovy.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liux.groovy.entity.GroovyScript;
import com.liux.groovy.service.IGroovyScriptService;
import com.liux.groovy.utils.SpringContextUtils;
import groovy.lang.GroovyClassLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 17:19
 * @description :groovy动态加载
 */
@Configuration
public class GroovyClassDynamicLoader implements ApplicationContextAware, InitializingBean {


    private static final GroovyClassLoader GROOVY_CLASS_LOADER = new GroovyClassLoader(GroovyClassDynamicLoader.class.getClassLoader());

    @Resource
    private IGroovyScriptService groovyScriptService;

    @Override
    public void afterPropertiesSet() {

        long start = System.currentTimeMillis();
        System.out.println("GroovyClassDynamicLoader开始解析groovy脚本...");
        init();

        long cost = System.currentTimeMillis() - start;
        System.out.println("GroovyClassDynamicLoader结束解析groovy脚本...，耗时：" + cost);
    }

    private void init() {
        List<GroovyScript> list = groovyScriptService.list(new LambdaQueryWrapper<GroovyScript>()
                .eq(GroovyScript::getBeanName, "test02")
                .eq(GroovyScript::getIsDelete, 0));
        GroovyScript groovyScript = list.get(0);
        SpringContextUtils.autowireBean(GROOVY_CLASS_LOADER.parseClass(groovyScript.getGroovyContent()), groovyScript.getBeanName());
    }

    public void refresh() {
        List<GroovyScript> list = groovyScriptService.list(new LambdaQueryWrapper<GroovyScript>()
                .eq(GroovyScript::getBeanName, "test02")
                .eq(GroovyScript::getIsDelete, 0));
        GroovyScript groovyScript = list.get(0);
        String beanName = groovyScript.getBeanName();
        if (SpringContextUtils.isExistBean(beanName)) {
            SpringContextUtils.removeBean(beanName);
        }
        SpringContextUtils.autowireBean(GROOVY_CLASS_LOADER.parseClass(groovyScript.getGroovyContent()), beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.setContext(applicationContext);
    }
}