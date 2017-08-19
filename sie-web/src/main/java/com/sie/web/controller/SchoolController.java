package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.SchoolEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.service.SchoolService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            SchoolEntity schoolEntity = this.schoolService.get(id);
            model.addAttribute("entity", JSON.toJSON(schoolEntity));
        }

        return "/school/addOrUpdate";
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

    @RequestMapping(value = "/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(@ModelAttribute SchoolEntity schoolEntity){
        ResultBean resultBean = new ResultBean();

        try{
            Integer id = this.schoolService.saveOrUpdate(schoolEntity);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("保存成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    @RequestMapping(value = "/delete.json")
    @ResponseBody
    public ResultBean delete(Integer id){
        ResultBean resultBean = new ResultBean();

        try{
            this.schoolService.delete(id);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("删除成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

}