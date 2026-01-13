package com.example.demo.concurrent;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.util.LinkedMultiValueMap;

public class Test {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    /*CallbackTask callbackTask = new CallbackTask();
    FutureTask<String> futureTask = new FutureTask<>(callbackTask);
    Thread thread1 = new Thread(futureTask);

    thread1.start();
    System.out.println("thread start");
    String x = futureTask.get();

    System.out.println(x);

    ExecutorService threadExecutor = new ThreadPoolExecutor(2,4,500, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
    FutureTask<String> futureTask1 = new FutureTask<>(callbackTask);
    threadExecutor.submit(futureTask1);
    System.out.println("thread executor is running");

    String y  = futureTask1.get();
    System.out.println(y);
    if(!threadExecutor.isTerminated()){
      threadExecutor.shutdownNow();*/
    /*Integer a   = 200;
    Integer b   = 200;
    System.out.println(a==b);
    System.out.println(a.equals( b));*/


    Map map  = new LinkedHashMap();
    map.put("a",1);
    map.put("a",1);
    map.put("b",2);
    map.put("c",3);

    System.out.println(map.keySet().size());


  }

}
