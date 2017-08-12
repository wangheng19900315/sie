package com.sie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangheng on 2017/8/10.
 */
@Controller
public class WelcomeController {


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/index1")
    public String index1(){
        return "index1";
    }
}
