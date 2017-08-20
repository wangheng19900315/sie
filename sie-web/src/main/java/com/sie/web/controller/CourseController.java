package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CourseEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.StudentEntity;
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
//            StudentEntity studentEntity = this.studentService.get(id);

        }
        //添加工程
        Map<Integer,String> projects = projectService.getAllCourseProject();
        model.addAttribute("projects", JSON.toJSON(projects));
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
    public ResultBean addOrupdate(CourseEntity courseEntity){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.courseService.saveOrUpdate(courseEntity);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("保存成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    @RequestMapping("/getCourseCheckbox.json")
    @ResponseBody
    public String getCourseCheckbox(Integer projectId, Integer systemType){
        String result = "";
        try{
            result = this.courseService.getCourseCheckbox(projectId, systemType);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}