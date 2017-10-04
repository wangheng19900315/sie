package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CourseEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.StudentEntity;
import com.sie.framework.type.School;
import com.sie.service.CourseService;
import com.sie.service.ProjectService;
import com.sie.service.bean.CourseBean;
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
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    private static final Logger LOGGER = Logger.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/list.html")
    public String list(){
        return "/course/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            CourseBean bean = this.courseService.getBean(id);
            model.addAttribute("entity", JSON.toJSON(bean));
        }
        model.addAttribute("schools", JSON.toJSON(School.getAll()));
        return "/course/addOrUpdate";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<CourseBean> listJons(Integer page, Integer rows ){

        PageInfo<CourseBean> pageInfo = null;
        try{
            pageInfo = this.courseService.getCourseList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }


    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(CourseBean courseBean){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.courseService.saveOrUpdate(courseBean);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("保存成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    @RequestMapping("/getCourses.json")
    @ResponseBody
    public List<CourseEntity> getCourseCheckbox(Integer projectId, Integer systemType){
//        Map<Integer,String> courses = new HashMap<>();
//        try{
//            courses = this.courseService.getCourses(projectId, systemType);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return this.courseService.getCourses(projectId, systemType);
    }

    @RequestMapping(value = "/delete.json")
    @ResponseBody
    public ResultBean delete(Integer id){
        ResultBean resultBean = new ResultBean();

        try{
            this.courseService.delete(id);
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