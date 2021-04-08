package com.stevenw.effctivejava;

import java.util.*;

/**
 * @author stevenw
 * @date 2020/6/30
 */
public class Singleton {
    public static final Singleton single = new Singleton();
    transient int a;
    private Singleton() {

    }

    public static Singleton getSingle() {
        return single;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }



    public static void main(String[] args) {

    }
}
