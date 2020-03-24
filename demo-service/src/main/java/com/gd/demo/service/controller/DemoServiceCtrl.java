package com.gd.demo.service.controller;

import com.gd.demo.api.DemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoServiceCtrl implements DemoApi {

    @Autowired
    @Qualifier("impl")
    private DemoApi demoApiImpl;

    @Override
    @GetMapping(value = "/hello")
    public String sayHello() {
        System.out.println("this is in service");
        return demoApiImpl.sayHello();
    }
}
