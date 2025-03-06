package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestControllerTest {

  @Autowired
  private TestController testController;

  @Test
  void test() {
    String s = testController.test();
    System.out.println(s);
  }
}
