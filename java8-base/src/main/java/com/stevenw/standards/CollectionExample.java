package com.stevenw.standards;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author stevenw
 * @date 2019/12/2
 */
public class CollectionExample {
    public static void isEmpty(){
        Collection collection = new ArrayList();
        //使用isEmpty检查空
        System.out.println(collection.isEmpty()); //true
        //使用CollectionUtils检查null和空。CollectionUtils工具包在spring和Apache common中都有
        System.out.println(CollectionUtils.isEmpty(collection)); //true
        collection = null;
        System.out.println(CollectionUtils.isEmpty(collection)); //true
    }

    public static void arrayInit(){
        //初始化集合最好能指定长度
        List list = new ArrayList(3);
    }

    /**
     * 数组的随机访问效率高
     * 当不知道list的内部实现是数组还是链表时，可以判断是否实现RandomAccess接口
     * 如果为true，则随机访问效率高
     */
    public static void RandomAccessList(){
        List list = Arrays.asList(new int[]{1,2,3});
        System.out.println(list instanceof RandomAccess); //true
        list = new LinkedList(list);
        System.out.println(list instanceof RandomAccess); //false
    }

    /**
     * 如果频繁使用 Collection.contains()判断集合中是否存在某个值，
     * 可以先将list转换成HashSet
     */
    public static void CollectionContains(){
        
    }

    public static void main(String[] args) {
        CollectionExample.RandomAccessList();
    }
}
