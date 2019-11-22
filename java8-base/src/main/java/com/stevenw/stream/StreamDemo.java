package com.stevenw.stream;

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
       testFilter();

//        Date date1 = new Date();
//        System.err.println(date1.toString());
//        Date date = new Date();
//        System.err.println(date.getTime());
//        System.err.println(date.after(date1));
//        testDistinct();
    }

    /**
     * 根据条件过滤元素
     */
    public static void testFilter(){
        list1 =  stream.filter(o ->  {return  !o.equals("2");}).collect(toList());
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
