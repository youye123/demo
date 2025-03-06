package com.example.demo.test;

public class SyncTest {

    private int j = 0;
    
    /**
     * 自增方法
     */
    public void incr(){
        //临界区代码--start
        for (int i = 0; i < 10000; i++) {
            j++;
        }
        //临界区代码--end
    }

    public int getJ() {
        return j;
    }
}

 class SyncMain {

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest = new SyncTest();
        Thread thread = new Thread(() -> syncTest.incr());
        Thread threadTwo = new Thread(() -> syncTest.incr());
        thread.start();
        threadTwo.start();
        thread.join();
        threadTwo.join();
        //最终打印结果是20000，如果不使用synchronized修饰，就会导致线程安全问题，输出不确定结果
        System.out.println(syncTest.getJ());
    }

}

