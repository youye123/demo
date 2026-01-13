package com.example.demo.concurrent.lock;

public class RaceConditionExample {
    // 共享资源
    private static int sharedCounter = 0;

    // 线程任务
    public static class CounterTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                sharedCounter++; // 对共享资源进行操作
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建多个线程
        Thread t1 = new Thread(new CounterTask());
        Thread t2 = new Thread(new CounterTask());
        Thread t3 = new Thread(new CounterTask());

        // 启动线程
        t1.start();
        t2.start();
        t3.start();

        // 等待所有线程完成
        t1.join();
        t2.join();
        t3.join();

        // 输出最终结果
        System.out.println("Expected Result: 30000");
        System.out.println("Actual Result: " + sharedCounter);
    }
}
