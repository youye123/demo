package com.example.demo.concurrent.lock.spin.impl;

import com.example.demo.concurrent.lock.spin.SpinLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;


/**
 * 自旋锁的简单实现
 */
public class SimpleSpinLock implements SpinLock {

  @Override
  public void lock() {

  }

  @Override
  public void lockInterruptibly() throws InterruptedException {

  }

  @Override
  public boolean tryLock() {
    return false;
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return false;
  }

  @Override
  public void unlock() {

  }

  @Override
  public Condition newCondition() {
    return null;
  }
}
