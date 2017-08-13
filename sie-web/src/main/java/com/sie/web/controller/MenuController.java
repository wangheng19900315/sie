package com.sie.web.controller;

import com.sie.framework.entity.SysMenuEntity;
import com.sie.framework.entity.SysRoleEntity;
import com.sie.service.MenuService;
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
@RequestMapping("/menu")
public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/user/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/menu/list";
    }

    @RequestMapping("/add.html")
    public String add(){
        return "/menu/add";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<SysMenuEntity> listJons(Integer page, Integer rows ){

        PageInfo<SysMenuEntity> pageInfo = null;
        try{
            pageInfo = this.menuService.getList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }

}