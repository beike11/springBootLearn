package com.stevenw.AQS;

import com.stevenw.thread.ThreadPoolUtils;
import org.apache.tomcat.util.threads.TaskThreadFactory;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author stevenw
 * @date 2019/7/29
 */
public class AqsDemo {
    public static void semaphoreDemo(){
        Date strat = new Date();
        final  int total = 500;
        final Semaphore semaphore  = new Semaphore(20);
        ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(100);
        CountDownLatch latch = new CountDownLatch(total);
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if(!executor.isShutdown()){
                    try {
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 150, 0, TimeUnit.SECONDS, blockingQueue,
                Executors.defaultThreadFactory(), handler);
        for (int i = 0; i < total; i++) {
            final int threadNum = i;
            threadPoolExecutor.execute(() -> {

//                    semaphore.acquire();
                System.err.println("threadNum: " + threadNum);
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
        Date end = new Date();
        System.out.println(end.getTime() - strat.getTime());
    }

    public static void test(int threadNum){
        try {
            Thread.sleep(1000);
            System.err.println("threadNum: " + threadNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static RejectedExecutionHandler myRejected(){
            RejectedExecutionHandler handler = new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    if(!executor.isShutdown()){
                        try {
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            return  handler;
    }
}
