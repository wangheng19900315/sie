package com.sie.web.controller;

import com.sie.framework.entity.StudentEntity;
import com.sie.service.bean.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangheng on 2017/8/20.
 */
@Controller
@RequestMapping("/api")
public class APIController {


    @RequestMapping("/index.html")
    public String index(){
        return "api";
    }
    /**
     * 学生注册
     * @return
     */
    @RequestMapping("/register.json")
    @ResponseBody
    public ResultBean register(StudentEntity studentEntity){

        return new ResultBean();
    }

    /**
    * 学生登录
    * @return
            */
    public String login(StudentEntity studentEntity){

        return null;
    }

    /**
     * 学生修改信息
     * @return
     */
    public String  updateStudent(StudentEntity studentEntity){

        return null;
    }

    /**
     * 获取学校信息
     * @return
     */
    public String  getSchool(StudentEntity studentEntity){

        return null;
    }



}
