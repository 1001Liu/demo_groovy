package com.liux.groovy.controller;

import com.liux.groovy.common.ReturnResult;
import com.liux.groovy.config.GroovyClassDynamicLoader;
import com.liux.groovy.croe.calculate.GroovyParser;
import com.liux.groovy.croe.config.GroovyDynamicLoader;
import com.liux.groovy.croe.entity.request.GroovyRequest;
import com.liux.groovy.service.IGroovyScriptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    private IGroovyScriptService groovyScriptService;

    @Resource
    private GroovyDynamicLoader dynamicLoader;

    @Resource
    private GroovyClassDynamicLoader groovyClassDynamicLoader;

    @Resource
    private GroovyParser groovyParser;

    @ResponseBody
    @RequestMapping("list")
    public ReturnResult list() {
        return ReturnResult.success(groovyScriptService.list());
    }

    @ResponseBody
    @PostMapping("calGroovy")
    public ReturnResult calGroovy(@RequestBody GroovyRequest request) {
        return ReturnResult.success(groovyParser.parse(request));
    }

    @ResponseBody
    @GetMapping("refGroovy")
    public ReturnResult refGroovy() {
        dynamicLoader.refresh();
        return ReturnResult.success();
    }


    @ResponseBody
    @GetMapping("refGroovy2")
    public ReturnResult refGroovy2() {
        groovyClassDynamicLoader.refresh();
        return ReturnResult.success();
    }
}
