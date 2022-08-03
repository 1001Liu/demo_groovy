package com.liux.groovy.controller;

import com.liux.groovy.common.ReturnResult;
import com.liux.groovy.croe.calculate.GroovyParser;
import com.liux.groovy.croe.entity.request.GroovyRequest;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;

/**
 * <p>
 * groovy脚本 前端控制器
 * </p>
 *
 * @author liuxin
 * @since 2022-07-28
 */
@Controller
@RequestMapping("/groovyScript")
public class GroovyScriptController {

    @Resource
    private GroovyParser groovyParser;

    @ResponseBody
    @PostMapping("calGroovy")
    public ReturnResult calGroovy(@RequestBody GroovyRequest request) {
        return ReturnResult.success(groovyParser.parse(request));
    }


    @ResponseBody
    @PostMapping("calGroovyClassLoader")
    public ReturnResult calGroovyClassLoader(@RequestBody GroovyRequest request) throws Exception {
        GroovyClassLoader loader = new GroovyClassLoader();
        String path = "D:\\work_space\\demo_groovy\\groovy-xml\\src\\main\\resources\\groovy\\TestGroovyXml.groovy";
        Class groovyClass = loader.parseClass(new File(path));
        GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
        groovyObject.invokeMethod("parse", request);
        return ReturnResult.success(groovyObject.invokeMethod("parse", request));
    }

}
