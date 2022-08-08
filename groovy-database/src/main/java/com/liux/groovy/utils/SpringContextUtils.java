package com.liux.groovy.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/8/7 13:31
 * @description :
 */
@Component
@Slf4j
public class SpringContextUtils implements ApplicationContextAware {

    static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.context = applicationContext;
    }

    public static void setContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtils.context == null) {
            SpringContextUtils.context = applicationContext;
        }
    }

    public static void autowireBean(Class clazz, String beanName) {
        DefaultListableBeanFactory defaultListableBeanFactory = getDefaultListableBeanFactory();
        // 通过BeanDefinitionBuilder创建bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());
    }


    public static boolean isExistBean(String name) {
        try {
            context.getBean(name);
            return true;
        } catch (Exception e) {
            log.warn("{}的bean不存在", name);
            return false;
        }
    }

    public static void removeBean(String beanName) {
        DefaultListableBeanFactory defaultListableBeanFactory = getDefaultListableBeanFactory();
        defaultListableBeanFactory.removeBeanDefinition(beanName);
    }

    public static DefaultListableBeanFactory getDefaultListableBeanFactory() {
        //将applicationContext转换为ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;

        // 获取bean工厂并转换为DefaultListableBeanFactory
        return (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
    }
}