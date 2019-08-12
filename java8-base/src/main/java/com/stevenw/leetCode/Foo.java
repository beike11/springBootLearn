package com.stevenw.leetCode;

import java.util.concurrent.Semaphore;

/**
 * @author stevenw
 * @date 2019/7/31
 */
public class Foo {
    private Semaphore one = new Semaphore(0);
    private Semaphore two = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        one.release();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        one.acquire();
        two.release();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {
        two.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
