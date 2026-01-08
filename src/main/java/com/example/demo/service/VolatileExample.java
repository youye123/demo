package com.example.demo.service;

public class VolatileExample {
    private volatile boolean flag = false;

    public void writer() {
        flag = true; // 写操作，立即对其他线程可见
    }

    public void reader() {
        while (!flag) {
            // 等待 flag 变为 true
        }
        System.out.println("Flag is now true!");
    }
}