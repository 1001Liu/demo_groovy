package com.liux.groovy.croe.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liux.groovy.croe.cache.GroovyInfo;
import com.liux.groovy.croe.cache.GroovyInnerCache;
import com.liux.groovy.croe.cache.GroovyMemoryResource;
import com.liux.groovy.croe.constant.GroovyConfigurationXmlWriter;
import com.liux.groovy.croe.constant.GroovyConstant;
import com.liux.groovy.croe.processor.CustomerScriptFactoryPostProcessor;
import com.liux.groovy.entity.GroovyScript;
import com.liux.groovy.service.IGroovyScriptService;
import groovy.lang.GroovyClassLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 17:19
 * @description :groovy动态加载
 */
@Configuration
public class GroovyDynamicLoader implements ApplicationContextAware, InitializingBean {

    private ConfigurableApplicationContext applicationContext;

    private static final GroovyClassLoader GROOVY_CLASS_LOADER = new GroovyClassLoader(GroovyDynamicLoader.class.getClassLoader());

    @Resource
    private IGroovyScriptService groovyScriptService;

    @Override
    public void afterPropertiesSet() throws Exception {

        long start = System.currentTimeMillis();
        System.out.println("开始解析groovy脚本...");

        init();

        long cost = System.currentTimeMillis() - start;
        System.out.println("结束解析groovy脚本...，耗时：" + cost);
    }

    private void init() {
        List<GroovyScript> list = groovyScriptService.list(new LambdaQueryWrapper<GroovyScript>()
                .eq(GroovyScript::getIsDelete, 0));
        List<GroovyInfo> groovyInfos = convert(list);
        init(groovyInfos);
    }

    private void init(List<GroovyInfo> groovyInfos) {

        if (CollectionUtils.isEmpty(groovyInfos)) {
            return;
        }

        GroovyConfigurationXmlWriter config = new GroovyConfigurationXmlWriter();

        addConfiguration(config, groovyInfos);

        put2map(groovyInfos);

        loadBeanDefinitions(config);
    }


    public void refresh() {

        List<GroovyScript> list = groovyScriptService.list(new LambdaQueryWrapper<GroovyScript>()
                .eq(GroovyScript::getIsDelete, 0));
        List<GroovyInfo> groovyInfos = convert(list);

        if (CollectionUtils.isEmpty(groovyInfos)) {
            return;
        }

        // loadBeanDefinitions 之后才会生效
        destroyBeanDefinition(groovyInfos);

        destroyScriptBeanFactory();

        GroovyConfigurationXmlWriter config = new GroovyConfigurationXmlWriter();

        addConfiguration(config, groovyInfos);

        put2map(groovyInfos);

        loadBeanDefinitions(config);
    }

    private List<GroovyInfo> convert(List<GroovyScript> list) {

        List<GroovyInfo> groovyInfos = new LinkedList<>();

        if (CollectionUtils.isEmpty(list)) {
            return groovyInfos;
        }
        groovyInfos = list.stream().map(groovy -> GroovyInfo.builder()
                .beanName(groovy.getBeanName())
                .groovyContent(groovy.getGroovyContent())
                .groovyName(groovy.getGroovyName())
                .path(groovy.getPath()).build()).collect(Collectors.toList());
        return groovyInfos;
    }


    private void addConfiguration(GroovyConfigurationXmlWriter config, List<GroovyInfo> groovyInfos) {
        for (GroovyInfo groovyInfo : groovyInfos) {
            writeBean(config, groovyInfo);
        }
    }

    private void loadBeanDefinitions(GroovyConfigurationXmlWriter config) {

        String contextString = config.getContent();

        if (StringUtils.isBlank(contextString)) {
            return;
        }

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) this.applicationContext.getBeanFactory());
        beanDefinitionReader.setResourceLoader(this.applicationContext);
        beanDefinitionReader.setBeanClassLoader(applicationContext.getClassLoader());
        beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this.applicationContext));

        beanDefinitionReader.loadBeanDefinitions(new GroovyMemoryResource(contextString));

        String[] postProcessorNames = applicationContext.getBeanFactory().getBeanNamesForType(CustomerScriptFactoryPostProcessor.class, true, false);

        for (String postProcessorName : postProcessorNames) {
            applicationContext.getBeanFactory().addBeanPostProcessor((BeanPostProcessor) applicationContext.getBean(postProcessorName));
        }
    }

    private void destroyBeanDefinition(List<GroovyInfo> groovyInfos) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        for (GroovyInfo groovyInfo : groovyInfos) {
            try {
                beanFactory.removeBeanDefinition(groovyInfo.getBeanName());
            } catch (Exception e) {
                System.out.println("【Groovy】delete groovy bean definition exception. skip:" + groovyInfo.getBeanName());
            }
        }
    }

    private void destroyScriptBeanFactory() {
        String[] postProcessorNames = applicationContext.getBeanFactory().getBeanNamesForType(CustomerScriptFactoryPostProcessor.class, true, false);
        for (String postProcessorName : postProcessorNames) {
            CustomerScriptFactoryPostProcessor processor = (CustomerScriptFactoryPostProcessor) applicationContext.getBean(postProcessorName);
            processor.destroy();
        }
    }

    private void writeBean(GroovyConfigurationXmlWriter config, GroovyInfo groovyInfo) {
        if (checkSyntax(groovyInfo)) {
            DynamicBean bean = composeDynamicBean(groovyInfo);
            config.write(GroovyConstant.SPRING_TAG, bean);
        }
    }

    private boolean checkSyntax(GroovyInfo groovyInfo) {
        try {
            // 校验脚本是否合格
            // 检查很耗时可以省去
            GROOVY_CLASS_LOADER.parseClass(groovyInfo.getGroovyContent());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private DynamicBean composeDynamicBean(GroovyInfo groovyInfo) {
        DynamicBean bean = new DynamicBean();
        String beanName = groovyInfo.getBeanName();

        Assert.notNull(beanName, "parser beanName cannot be empty!");

        //设置bean的属性

        bean.put("id", beanName);
        String groovyName = groovyInfo.getGroovyName();
        Assert.notNull(groovyName, "parser groovyName cannot be empty!");
        bean.put("script-source", GroovyConstant.SCRIPT_SOURCE_PREFIX + groovyName);

        return bean;
    }

    private void put2map(List<GroovyInfo> groovyInfos) {
        GroovyInnerCache.put2beanMap(groovyInfos);
        GroovyInnerCache.put2groovyMap(groovyInfos);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }
}