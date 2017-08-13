package com.sie.web.controller;

import com.sie.framework.entity.RoleEntity;
import com.sie.service.RoleService;
import com.sie.service.bean.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private static final Logger LOGGER = Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/user/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/role/list";
    }

    @RequestMapping("/add.html")
    public String add(){
        return "/role/add";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<RoleEntity> listJons(Integer page, Integer rows ){

        PageInfo<RoleEntity> pageInfo = null;
        try{
            pageInfo = this.roleService.getList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }

}