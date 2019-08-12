package com.stevenw.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stevenw
 * @date 2019/7/19
 */
public class ThreadPoolUtils {
    //int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit
    public static ThreadPoolExecutor createThreadPool() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 10,
                TimeUnit.SECONDS, blockingQueue, (ThreadFactory) Thread::new, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(() -> sleep(100));
        threadPoolExecutor.execute(() -> sleep(100));
        threadPoolExecutor.execute(() -> sleep(100));

        int activeCount = -1;
        int queueSize = -1;

            if (activeCount != threadPoolExecutor.getActiveCount()
                    || queueSize != threadPoolExecutor.getQueue().size()) {
                System.out.println("活跃线程个数 " + threadPoolExecutor.getActiveCount());
                System.out.println("核心线程个数 " + threadPoolExecutor.getCorePoolSize());
                System.out.println("队列线程个数 " + threadPoolExecutor.getQueue().size());
                System.out.println("最大线程数 " + threadPoolExecutor.getMaximumPoolSize());
                System.out.println("------------------------------------");
                activeCount = threadPoolExecutor.getActiveCount();
                queueSize = threadPoolExecutor.getQueue().size();
            }
        return threadPoolExecutor;
    }

    private static void sleep(long value) {
        try {
            System.out.println(Thread.currentThread().getName() + "线程执行sleep方法");
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
