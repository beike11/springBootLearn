package com.stevenw.test;

import com.stevenw.objectUtils.Data;
import com.stevenw.thread.ThreadPoolUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * threadlocal用来隔离资源，避免多线程中的资源竞争，是一种用空间换时间的方案
 * 不同的线程中使用 变量副本
 *  常用场景，各种connection连接
 *
 * @author stevenw
 * @date 2019/11/21
 */
public class ThreadLocalTest {

    public static Data getData(){
        Data data = new Data();
        data.setId(1);
        return data;
    }

    @org.junit.Test
    public void test(){
        CountDownLatch countDownLatch = new CountDownLatch(4);
        // a & (b - 1) = a % b  ,b必须为2^n

        ThreadLocal<Data> threadLocal = new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return ThreadLocalTest.getData();
            }
        };

        ExecutorService executorService =  ThreadPoolUtils.getThreadPoolExecutor(10);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Data data1 =  threadLocal.get();
                data1.setId(2);
                System.out.println("thread1：" + data1.getId());
                countDownLatch.countDown();
            }
        };
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {

                Data data1 =  threadLocal.get();
                data1.setId(3);
                System.out.println("thread2：" + data1.getId());
                countDownLatch.countDown();
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread3：" + threadLocal.get().getId());
                countDownLatch.countDown();
            }
        };
        executorService.execute(runnable);
        executorService.execute(runnable1);
        executorService.execute(runnable2);
        executorService.shutdown();
        countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
