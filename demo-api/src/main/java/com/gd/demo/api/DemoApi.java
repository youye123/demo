package com.gd.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "demo-service",qualifier = "impl")
public interface DemoApi {

    @GetMapping(value = "/hello")
    String sayHello();
}
