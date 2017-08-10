package com.sie.web.controller;

import com.sie.framework.entity.UserEntity;
import com.sie.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/showInfo/{userId}")
    public String showUserInfo(ModelMap modelMap, @PathVariable String userId){
        LOGGER.info("查询用户：" + userId);
         UserEntity userEntity = userService.load(userId);
        modelMap.addAttribute("userInfo", userEntity);
        return "/user/showInfo";
    }

    @RequestMapping("/showInfos")
    @ResponseBody
    public List<UserEntity> showUserInfos(){
        LOGGER.info("查询用户全部用户");
        List<UserEntity> userInfos = userService.findAll();
        return userInfos;
    }

    public static void main(String args[])
    {

        String url = "jdbc:mysql://localhost:3306/uie";
        String driver = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driver);
        }catch(Exception e){
            System.out.println("无法加载驱动");
        }

        try {
            Connection con = DriverManager.getConnection(url,"root","123456");
            if(!con.isClosed())
                System.out.println("success");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}