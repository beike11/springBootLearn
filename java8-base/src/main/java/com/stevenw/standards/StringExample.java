package com.stevenw.standards;

/**
 * @author stevenw
 * @date 2019/12/4
 */
public class StringExample {
    /**
     * 拼接字符串使用StringBuilder
     * 例外，如果不是循环拼接，可以使用 "+",如：
     *  "is"+"string"
     */
    public static void StringSplice(){
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
             stringBuilder.append(i);
        }
        result = stringBuilder.toString();
        System.out.println(result);
    }

    public static void main(String[] args) {
        StringExample.StringSplice();
    }
}
