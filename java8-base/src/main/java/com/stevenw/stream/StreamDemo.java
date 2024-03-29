package com.stevenw.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

/**
 * @author stevenw
 * @date 2019/9/26
 */
public class StreamDemo {
    private static List<String> list1 = new ArrayList<>();
   private static Stream<String> stream;
       static {
           list1.add("1");
           list1.add("2");
           list1.add("3");
           list1.add("2");
           stream = list1.stream();
       }

    public static void main(String[] args) {
        System.out.println(list1.toString());
        list1.add("a");
        System.out.println(list1.toString());
    }

    /**
     * 根据条件过滤元素
     */
    public static void testFilter(){
         stream.filter(o ->  {return  !o.equals("2");}).collect(toList());
        list1.stream().forEach(System.out::println);

    }

    /**
     * 去重
     * 需要定义equals方法
     */
    public static void testDistinct(){
        list1 = stream.distinct().collect(toList());
        list1.stream().forEach(System.out::println);
    }

    /**
     * 排序
     * sorted() / sorted((T, T) -> int)
     */
    public static void testSorted(){

    }

    /**
     * 返回前两个元素
     */
    public static void testLimit(){
        stream.limit(2);
    }

    }
