package com.liux.groovy.croe.processor;

import com.liux.groovy.croe.constant.GroovyConstant;
import com.liux.groovy.croe.entity.DatabaseScriptSource;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.scripting.support.ScriptFactoryPostProcessor;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 14:16
 * @description :扩展自定义加载groovy,实现定制的Groovy脚本后处理器，用来定制数据库的脚本加载处理
 */
@Component
public class CustomerScriptFactoryPostProcessor extends ScriptFactoryPostProcessor {
    @NotNull
    @Override
    protected ScriptSource convertToScriptSource(@NotNull String beanName, String scriptSourceLocator, @NotNull ResourceLoader resourceLoader) {

        if (scriptSourceLocator.startsWith(INLINE_SCRIPT_PREFIX)) {
            return new StaticScriptSource(scriptSourceLocator.substring(INLINE_SCRIPT_PREFIX.length()), beanName);
        }
        if (scriptSourceLocator.startsWith(GroovyConstant.SCRIPT_SOURCE_PREFIX)) {
            return new DatabaseScriptSource(StringUtils.substringAfter(scriptSourceLocator, GroovyConstant.SCRIPT_SOURCE_PREFIX));
        }
        return new ResourceScriptSource(resourceLoader.getResource(scriptSourceLocator));
    }

}
