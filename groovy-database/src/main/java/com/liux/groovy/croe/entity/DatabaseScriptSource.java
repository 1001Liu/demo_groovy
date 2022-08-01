package com.liux.groovy.croe.entity;

import com.liux.groovy.croe.cache.GroovyInfo;
import com.liux.groovy.croe.cache.GroovyInnerCache;
import org.springframework.scripting.ScriptSource;
import org.springframework.util.StringUtils;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 14:21
 * @description :groovy脚本数据源
 */
public class DatabaseScriptSource implements ScriptSource {


    private String scriptName;

    public DatabaseScriptSource(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public String getScriptAsString() {
        // 从内部缓存获取
        GroovyInfo groovyInfo = GroovyInnerCache.getByGroovyName(scriptName);
        if (groovyInfo != null) {
            return groovyInfo.getGroovyContent();
        } else {
            return "";
        }
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public String suggestedClassName() {
        return StringUtils.stripFilenameExtension(this.scriptName);
    }
}
