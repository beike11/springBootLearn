package com.stevenw.standards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author stevenw
 * @date 2019/12/4
 */
public class ExceptionExample {
    /**
     * java 1.7后引入了try-with-resources
     * 该语句能保证相关资源关闭，优于原来的try-catch-finally
     * 并且使用程序更简洁安全
     */
    public static void tryWithResources(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:/Temp/123.txt"))){
            String line;
            while ((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        }catch (IOException e){

        }
    }

    public static void main(String[] args) {
        ExceptionExample.tryWithResources();
    }
}
