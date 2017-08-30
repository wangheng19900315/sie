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

    @RequestMapping("/")
    public String welcome(){
        return "index";
    }



    @RequestMapping(value = "/login.html")
    public String login(){
        return "login";
    }
}
