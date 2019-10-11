package com.stevenw.AQS;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.stevenw.thread.ThreadPoolUtils;
import org.apache.tomcat.util.threads.TaskThreadFactory;

import java.util.Date;
import java.util.Timer;
import java.util.concurrent.*;

/**
 * @author stevenw
 * @date 2019/7/29
 */
public class AqsDemo {
    public static void semaphoreDemo() {

        Date strat = new Date();
        final int total = 500;
        final Semaphore semaphore = new Semaphore(20);
        ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(100);
        CountDownLatch latch = new CountDownLatch(total);
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (!executor.isShutdown()) {
                    try {
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setNameFormat("demo-pool-%d");
        ThreadFactory threadNameFactory = threadFactoryBuilder.build();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(300, 500, 0, TimeUnit.SECONDS, blockingQueue,
                threadNameFactory, handler);
        for (int i = 0; i < total; i++) {
            final int threadNum = i;
            threadPoolExecutor.execute(() -> {
                String name = Thread.currentThread().getName();
//                    semaphore.acquire();
                System.err.println(name + ": " + threadNum);
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
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

    public static void test(int threadNum) {
        try {
            Thread.sleep(1000);
            System.err.println("threadNum: " + threadNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static RejectedExecutionHandler myRejected() {
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (!executor.isShutdown()) {
                    try {
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        return handler;
    }

    public static void executorsDemo() throws InterruptedException {
        //创建线程工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        ExecutorService cacheService = Executors.newCachedThreadPool();
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService fixedService = Executors.newFixedThreadPool(10);
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        ExecutorService singleService = Executors.newSingleThreadExecutor();
        //创建一个定长线程池，支持定时及周期性任务执行。
        Executors.newSingleThreadScheduledExecutor();

        ThreadFactory threadFactory1 = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ScheduledExecutorService timer1 = Executors.newScheduledThreadPool(10, threadFactory1);

        Runnable runnable = new MyRun();
        CountDownLatch countDownLatch = new CountDownLatch(50);
        timer1.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.err.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        }, 0, 1, TimeUnit.SECONDS);
        countDownLatch.await();
        timer1.shutdown();
//        timer.
    }
}
