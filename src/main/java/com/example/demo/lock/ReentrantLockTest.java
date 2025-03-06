package com.example.demo.lock;


import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

  private static int j = 0;
  private static final ReentrantLock lock = new ReentrantLock();

  public static void main(String[] args) throws InterruptedException {

    Thread t1 = new Thread(new IncreaseTask());
    Thread t2 = new Thread(new IncreaseTask());

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("j = " + j);
  }

  public static class IncreaseTask implements Runnable {

    @Override
    public void run() {
      // 普通场景
      /*for (int i = 0; i < 10000; i++) {
        j++;
      }
      synchronized (IncreaseTask.class) {
        for (int i = 0; i < 10000; i++) {
          j++;
        }
      }*/

      for (int i = 0; i < 10000; i++) {
        lock.lock();
        try {
          j++;
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
        System.out.println(
            "threadName: " + Thread.currentThread().getName() + ", j = " + j + ", i = " + i + "\n");
      }
    }
  }
}
