package com.sie.web.controller;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.LogEntity;
import com.sie.service.LogService;
import com.sie.service.bean.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/log")
public class LogController {

    private static final Logger LOGGER = Logger.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @RequestMapping("/list.html")
    public String list(){
        return "/log/list";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<LogEntity> listJons(String comment,Integer page, Integer rows ){

        List<HqlOperateVo> voList = new ArrayList<>();
        voList.add(new HqlOperateVo("comment","like",comment));
        PageInfo<LogEntity> pageInfo = null;
        try{
            pageInfo = this.logService.getList(page,rows, voList);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }
}