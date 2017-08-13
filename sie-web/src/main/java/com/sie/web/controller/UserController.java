package com.sie.web.controller;

import com.sie.framework.entity.UserEntity;
import com.sie.service.UserService;
import com.sie.framework.base.PageInfo;
import com.sie.service.bean.ResultBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/user/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/user/list";
    }

    @RequestMapping("/add.html")
    public String add(){
        return "/user/add";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<UserEntity> listJons(Integer page, Integer rows ){

        PageInfo<UserEntity> pageInfo = null;
        try{
            pageInfo = this.userService.getList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(UserEntity userEntity){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.userService.saveOrUpdate(userEntity);
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }




}