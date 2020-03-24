package com.gd.demo.server;

import com.gd.demo.api.DemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApiController {

    @Autowired
    private DemoApi demoApi;

    @GetMapping(value = "/hello")
    public String hello(){
        System.out.println("this is in server");
        return demoApi.sayHello();
    }
}
