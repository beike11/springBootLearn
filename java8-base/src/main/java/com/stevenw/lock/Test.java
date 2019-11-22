package com.stevenw.lock;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author stevenw
 * @date 2019/10/18
 */
public class Test {
    public static void main(String[] args) {
        testInsert();
    }
    public  static void testInsert(){
        CountDownLatch countDownLatch = new CountDownLatch(200);
        ReentrantLockUtils reentrantLockUtils = new ReentrantLockUtils();
        long t1 =System.currentTimeMillis();
        for (int i = 0; i < 200; i++){
            new Thread(){
                @Override
                public void run() {
                    reentrantLockUtils.insert(Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            }.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long t2 =System.currentTimeMillis();
        System.out.println(t2-t1);
        ArrayList temp = reentrantLockUtils.getArrayList();
        StringBuffer a = new StringBuffer();
        temp.stream().forEach(o -> {
            a.append(o+",");
        });
        System.err.println(a.toString());
    }

    public static void interruptionTest(){
        ReentrantLockUtils reentrantLockUtils = new ReentrantLockUtils();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    Boolean a = Thread.currentThread().isInterrupted();
                    System.out.println("in run() - about to sleep for 20 seconds-------" + a);
                    try {
                        Thread.sleep(2000);
                        System.out.println("in run() - woke up");
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(runnable);

        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in main() - interrupting other thread");
        t.interrupt();
        System.out.println(t.isInterrupted());
        System.out.println(t.isInterrupted());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.isAlive());
        System.out.println("in main() - leaving");
    }
}
