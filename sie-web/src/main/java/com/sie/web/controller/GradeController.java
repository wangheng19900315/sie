package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CrEntity;
import com.sie.framework.entity.GradeEntity;
import com.sie.service.CrService;
import com.sie.service.GradeService;
import com.sie.service.bean.GradeBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/grade")
public class GradeController {

    private static final Logger LOGGER = Logger.getLogger(GradeController.class);

    @Autowired
    private GradeService gradeService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/cr/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/grade/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            GradeBean gradeBean = this.gradeService.getBean(id);
            model.addAttribute("bean", JSON.toJSON(gradeBean));
        }

        return "/grade/addOrUpdate";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<GradeBean> listJons(Integer page, Integer rows, String studentName ){

        PageInfo<GradeBean> pageInfo = null;
        try{
            pageInfo = this.gradeService.getOrderDetailList(page,rows, studentName);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(GradeEntity gradeEntity){
        ResultBean resultBean = new ResultBean();

        try{
            Integer id = this.gradeService.updateGrade(gradeEntity);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("保存成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }




}