package com.stevenw.web;

import com.stevenw.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author stevenw
 * @date 2019/7/17
 */
@RestController
public class HelloHandler {
    @Autowired
    private TestService testService;
    @GetMapping("/listAll1")
    public List<Map<String,Object>> listAllOne(){
        System.err.println("111");
        return  testService.listAllOne();
    }

    @GetMapping("/listAll2")
    public List<Map<String,Object>> listAllTwo(){
        return  testService.listAllTwo();
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("110");
        BigDecimal b = new BigDecimal("1.1");
        System.err.println(a.add(a));
        System.err.println(a.add(b));

    }
}
