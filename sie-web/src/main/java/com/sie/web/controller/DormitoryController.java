package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.DormitoryEntity;
import com.sie.service.DormitoryService;
import com.sie.service.ProjectService;
import com.sie.service.bean.DormitoryBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/dormitory")
public class DormitoryController {

    private static final Logger LOGGER = Logger.getLogger(DormitoryController.class);

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/coupon/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/dormitory/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            DormitoryEntity dormitoryEntity = this.dormitoryService.get(id);
            model.addAttribute("entity", JSON.toJSON(dormitoryEntity));
        }
        //添加工程
        Map<Integer,String> projects = projectService.getAllCourseProject();
        model.addAttribute("projects", JSON.toJSON(projects));
        return "/dormitory/addOrUpdate";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<DormitoryBean> listJons(Integer page, Integer rows ){

        PageInfo<DormitoryBean> pageInfo = null;
        try{
            pageInfo = this.dormitoryService.getDormitoryList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(DormitoryEntity dormitoryEntity){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.dormitoryService.saveOrUpdate(dormitoryEntity);
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

        //TODO 判断是否有订单引用有则不能进行删除

        try{
            this.dormitoryService.delete(id);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("删除成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    //获取项目下的住宿
    @RequestMapping("/getDormitory.json")
    @ResponseBody
    public DormitoryBean getDormitory(Integer projectId){
        return this.dormitoryService.getDormitoryByProjectId(projectId);
    }


}