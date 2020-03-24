package com.gd.demo.service.Impl;

import com.gd.demo.api.DemoApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("impl")
public class DemoApiImpl implements DemoApi {

    @Override
    public String sayHello() {
        return "hello ,this is demo service";
    }
}
