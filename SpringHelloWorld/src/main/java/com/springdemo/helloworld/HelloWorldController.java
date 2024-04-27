package com.springdemo.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping
    public String sayHello() {
        return "Hello, World!";
    }

    @RequestMapping("/goodbye")
    public String sayGoodbye() {
        return "Goodbye, World!";
    }

}
