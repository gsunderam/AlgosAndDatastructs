package com.gs.home.springboot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Sample project to show Spring Boot
 * @author chandrashekar
 *
 */
@Controller
@EnableAutoConfiguration
@SpringBootApplication //Reqd to distinguish this as a spring boot app if many main classes
//are there in the workspace
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
