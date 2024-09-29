package com.example.demo.concurrent;

import java.util.concurrent.Callable;

public class CallbackTask implements Callable<String> {

  @Override
  public String call() throws Exception {
    Thread.sleep(2000L);
    String a = "do something good!";
    return a;
  }
}
