package com.stevenw.AQS;

/**
 * @author stevenw
 * @date 2019/8/13
 */
public class MyRun implements Runnable{

    public MyRun() {
        System.out.println("myRun");
    }

    @Override
    public void run() {
        System.err.println("11");
    }
}
