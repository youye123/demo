package com.example.demo.test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
  public static void main(String[] args) {
    /*// 创建一个包含 2000 个随机数的数组
    long[] array = new long[2000];
    long expectedSum = 0;
    Random random = new Random(0);

    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(10000);
      expectedSum += array[i];
    }
    System.out.println("Expected sum: " + expectedSum);

    // 创建 ForkJoinTask 并提交到 ForkJoinPool
    SumTask task = new SumTask(array, 0, array.length);
    long startTime = System.currentTimeMillis();
    Long result = ForkJoinPool.commonPool().invoke(task);
    long endTime = System.currentTimeMillis();

    System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");*/

    long[] array = new long[20];
    for (int i = 0; i < array.length; i++) {
      array[i] = i;
    }

    printTask task = new printTask(array, 0, array.length);
    ForkJoinPool.commonPool().invoke(task);
  }
}

class printTask extends RecursiveAction {

  static final int THRESHOLD = 2; // 阈值，任务大小小于等于该值时直接计算
  long[] array;
  int start;
  int end;

  public printTask(long[] array, int start, int end) {
    this.array = array;
    this.start = start;
    this.end = end;
  }

  @Override
  protected void compute() {
    if (end - start <= THRESHOLD) {
      System.out.println("print from " + start + " to " + end);
      for (int i = start; i < end; i++) {
        System.out.println(array[i]);
      }
    } else {
      // 任务太大，一分为二
      int middle = (start + end) / 2;
      printTask leftTask = new printTask(array, start, middle);
      printTask rightTask = new printTask(array, middle, end);
      leftTask.fork();
      rightTask.fork();

      leftTask.join();
      rightTask.join();
    }
  }
}


class SumTask extends RecursiveTask<Long> {

  static final int THRESHOLD = 10; // 阈值，任务大小小于等于该值时直接计算
  long[] array;
  int start;
  int end;

  public SumTask(long[] array, int start, int end) {
    this.array = array;
    this.start = start;
    this.end = end;
  }

  @Override
  protected Long compute() {
    if (end - start <= THRESHOLD) {
      System.out.println("Computing sum from " + start + " to " + end);
      // 如果任务足够小，直接计算
      long sum = 0;
      for (int i = start; i < end; i++) {
        sum += array[i];
      }
      return sum;
    } else {
      // 任务太大，一分为二
      int middle = (start + end) / 2;
      SumTask leftTask = new SumTask(array, start, middle);
      SumTask rightTask = new SumTask(array, middle, end);

      // 并行执行子任务
      leftTask.fork();
      rightTask.fork();

      // 等待子任务完成并合并结果
      Long leftResult = leftTask.join();
      Long rightResult = rightTask.join();

      return leftResult + rightResult;
    }
  }
}
