package com.example.demo.concurrent.future.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture 测试类
 * 测试 CompletableFuture 任务的创建、编排、回调等特性
 *
 * @since 2026/01/10
 */
public class CompletableFutureTest {

  static class CompletableFutureA {
    int testA() throws InterruptedException {
      Thread.sleep(3000);
      System.out.println(Thread.currentThread().getName());
      return 1;
    }
  }

  static class CompletableFutureB {
    int testB() throws InterruptedException {
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName());
      return 2;
    }
  }

  // A 和 B 各自异步执行
  public static int testAsyncRunAB() {

    int finalResult = 0;

    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
      int result;
      try {
        result = new CompletableFutureA().testA();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return result;
    });

    CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> {
      int result;
      try {
        result = new CompletableFutureB().testB();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return result;
    });

    try {
      finalResult = futureB.get() + future.get();
    }catch (Exception e){
      e.printStackTrace();
    }
    return finalResult;
  }

  // A执行完成后才能执行B
  public static int testAsyncRunBAfterA() {
    int finalResult = 0;
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
      int result;
      try {
        result = new CompletableFutureA().testA();
      }catch (Exception e){
        throw new RuntimeException(e);
      }
      return result;
    });

    /*CompletableFuture<Integer> futureResult  = future.thenApplyAsync(result -> {
      int resultB;
      try {
        resultB = new CompletableFutureB().testB();
      }catch (Exception e){
        throw new RuntimeException(e);
      }
      return result + resultB;
    });*/

    CompletableFuture<Void> futureResult  = future.thenAccept(result -> {
      int resultB;
      try {
        resultB = new CompletableFutureB().testB();
      }catch (Exception e){
        throw new RuntimeException(e);
      }
    });

    try {
      futureResult.get();
    }catch (Exception e){
      e.printStackTrace();
    }
    return finalResult;
  }


  // AB任务结合
  public static int testAsyncRunAAndB() {
    int finalResult = 0;
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
      int result;
      try {
        result = new CompletableFutureA().testA();
      }catch (Exception e){
        throw new RuntimeException(e);
      }
      return result;
    });

    CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> {
      int result;
      try {
        result = new CompletableFutureB().testB();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return result;
    }).whenComplete((result, ex) -> {
      System.out.println("futureB result:" + result);
    });

    CompletableFuture<Integer> futureResult  = future.thenCombine(futureB, Integer::sum);

    try {
      finalResult = futureResult.get();
    }catch (Exception e){
      e.printStackTrace();
    }
    return finalResult;
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    int result = testAsyncRunAAndB();
    System.out.println(result);
  }
}
