package com.liux.groovy.controller;

import com.liux.groovy.common.ReturnResult;
import com.liux.groovy.croe.calculate.GroovyParser;
import com.liux.groovy.croe.entity.request.GroovyRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private GroovyParser groovyParser;

    @ResponseBody
    @PostMapping("calGroovy")
    public ReturnResult calGroovy(@RequestBody GroovyRequest request) {
        return ReturnResult.success(groovyParser.parse(request));
    }

}
