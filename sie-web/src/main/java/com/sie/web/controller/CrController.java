package com.sie.web.controller;

import com.sie.framework.entity.CrEntity;
import com.sie.service.CrService;
import com.sie.service.bean.PageInfo;
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
@RequestMapping("/cr")
public class CrController {

    private static final Logger LOGGER = Logger.getLogger(CrController.class);

    @Autowired
    private CrService crService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/cr/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/cr/list";
    }

    @RequestMapping("/add.html")
    public String add(){
        return "/cr/add";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<CrEntity> listJons(Integer page, Integer rows ){

        PageInfo<CrEntity> pageInfo = null;
        try{
            pageInfo = this.crService.getList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(CrEntity crEntity){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.crService.saveOrUpdate(crEntity);
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }




}