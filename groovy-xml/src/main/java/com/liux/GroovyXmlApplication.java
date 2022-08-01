package com.liux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author 11512
 */
@SpringBootApplication
@ImportResource("classpath:spring-groovy.xml")
public class GroovyXmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroovyXmlApplication.class, args);
    }

}
