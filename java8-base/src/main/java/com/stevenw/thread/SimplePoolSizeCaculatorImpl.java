package com.stevenw.thread;

import java.util.concurrent.BlockingQueue;

/**
 * @author stevenw
 * @date 2019/7/29
 */
public class SimplePoolSizeCaculatorImpl extends PoolSizeCalculator{
    @Override
    protected Runnable creatTask() {
        return null;
    }

    @Override
    protected BlockingQueue createWorkQueue() {
        return null;
    }

    @Override
    protected long getCurrentThreadCPUTime() {
        return 0;
    }
}
