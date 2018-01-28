package com.sie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public String welcome(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("http://www.truabroad.cn/tru/cn/index/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        //return "index";
    }



    @RequestMapping(value = "/login.html")
    public String login(){
        return "login";
    }
}
