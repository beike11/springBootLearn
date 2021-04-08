package com.stevenw.effctivejava.builder;

import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author stevenw
 * @date 2020/7/2
 */
public class TestBuilder {
    public static void main(String[] args) {
       System.out.println(17/.75f);
       int a= Math.max((int) (17/.75f) + 1, 16);
       System.out.println(a);
    }
}
