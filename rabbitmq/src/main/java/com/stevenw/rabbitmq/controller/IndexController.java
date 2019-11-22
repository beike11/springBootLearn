package com.stevenw.rabbitmq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author stevenw
 * @date 2019/10/28
 */
@Controller
public class IndexController {
    @GetMapping("/getIndex")
    public String indexPage(){
        System.err.println(123);
        return "index";
    }
}
