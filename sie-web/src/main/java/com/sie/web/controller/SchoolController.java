package com.sie.web.controller;

import com.sie.framework.entity.SchoolEntity;
import com.sie.framework.entity.SysMenuEntity;
import com.sie.service.SchoolService;
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
@RequestMapping("/school")
public class SchoolController {

    private static final Logger LOGGER = Logger.getLogger(SchoolController.class);

    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/user/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/school/list";
    }

    @RequestMapping("/add.html")
    public String add(){
        return "/school/add";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<SchoolEntity> listJons(Integer page, Integer rows ){

        PageInfo<SchoolEntity> pageInfo = null;
        try{
            pageInfo = this.schoolService.getList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }

}