package com.stevenw.lock;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 *
 * @author stevenw
 * @date 2019/10/18
 */
public class ReentrantLockUtils {
    private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
    public static volatile AtomicInteger index = new AtomicInteger(0);
    private Lock lock = new ReentrantLock();

    public void insert(String name) {
        try {
            lock.lock();
            System.out.println(name + "得到锁");
            arrayList.add(index.getAndIncrement());
            Thread.sleep(200);
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(name + "释放锁");
        }
    }

    public void testInterruption ()  {
        while (true){

                Boolean a = Thread.currentThread().isInterrupted();
                System.out.println("in run() - about to sleep for 20 seconds-------" + a);

            }
           /* if(Thread.interrupted()){
                break;
            }*/

    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }


}
