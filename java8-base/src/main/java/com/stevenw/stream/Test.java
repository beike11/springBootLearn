package com.stevenw.stream;

import com.stevenw.objectUtils.DeepClone;

import java.io.Serializable;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author stevenw
 * @date 2019/12/26
 */
public class Test implements Serializable {
    Integer a;
    final int b = 1;
    public static void main(String[] args) throws KeyStoreException {
//        Test test = new Test2();
////        Test test1 = new Test3();
////        test.A();
////        test1.A();
        KeyStore keyStore = KeyStore.getInstance("JKS");
        System.out.println(keyStore.getType());
        Test test = new Test();
        System.out.println(test.get1("1"));
    }

    public void  A(){
        System.out.println("我是A");
    }

    public boolean A(int [] a){
        System.out.println(111);
        return true;
    }


    public  <E extends Comparable<E>> Optional<E> get1(E a){
        E result = a;
        return Optional.of(result);
    }

}
