package com.steven.bknote.jackson;

import com.steven.bknote.result.Result;

/**
 * @author stevenw
 * @date 2020/5/6
 */
public class MyJsonFilter {

    public static void main(String[] args) {
        Result userInfo  = new Result();
        userInfo.setCode(11);
        Integer a = userInfo.getCode();
        userInfo.setCode(null);
        System.out.println(a);
        System.out.println(userInfo.getCode());
    }
}
