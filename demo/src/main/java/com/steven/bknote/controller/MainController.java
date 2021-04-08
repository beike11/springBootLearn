package com.steven.bknote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author stevenw
 * @date 2020/5/6
 */
@Controller
public class MainController {
    @GetMapping("/main/nav")
    public String navController(){
        return "templates/nav.html";
    }


}
