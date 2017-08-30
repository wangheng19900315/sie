package com.sie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.entity.StudentEntity;
import com.sie.service.StudentService;
import com.sie.service.bean.ResultBean;
import com.sie.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by wangheng on 2017/8/20.
 */
@Controller
@RequestMapping("/api")
public class APIController {

    @Autowired
    private StudentService studentService;


    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @RequestMapping("/index.html")
    public String index(){
        return "api";
    }

    private static final String SYSTEM_ACCESS_TOKEN="un23n4no2bu4bs34";
    /**
     * 学生注册
     * @return
     */
    @RequestMapping("/register.json")
    @ResponseBody
    public ResultBean register(String params, String accessToken){
        logger.info("register.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String email = maps.get("email");
            String weixin = maps.get("weixin");
            String password = maps.get("password");

            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }
            if(StringUtil.isBlank(password)){
                resultBean.setMessage("密码 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(email) && StringUtil.isBlank(weixin)){
                resultBean.setMessage("用户名 为空，请检查参数");
                return resultBean;
            }


            resultBean = this.studentService.register(email, weixin, password);
        }catch(Exception e){
            e.printStackTrace();
        }


        return resultBean;
    }

    /**
    * 学生登录
    * @return
    */
    @RequestMapping("/login.json")
    @ResponseBody
    public ResultBean login(String params, String accessToken){
        logger.info("login.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String userName = maps.get("userName");
            String password = maps.get("password");

            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(password)){
                resultBean.setMessage("密码 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(userName)){
                resultBean.setMessage("用户名 为空，请检查参数");
                return resultBean;
            }


            StudentEntity studentEntity  = this.studentService.login(userName, password);
            if(studentEntity != null){
                resultBean.setMessage("登录成功");
                resultBean.setSuccess(true);
                resultBean.setData(studentEntity);
            }else{
                resultBean.setMessage("用户名或者密码错误，请检查信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 学生修改信息
     * @return
     */
    @RequestMapping("/updateStudent.json")
    @ResponseBody
    public ResultBean  updateStudent(String params, String accessToken){

        logger.info("updateStudent.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            ObjectMapper mapper = new ObjectMapper();
            StudentEntity studentEntity = mapper.readValue(params, StudentEntity.class);


            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            resultBean = this.studentService.updateEntity(studentEntity);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;

    }

    /**
     * 获取学校信息
     * @return
     */
    public String  getSchool(String params, String accessToken){
        logger.info("getSchool.json params="+params +" accessToken="+accessToken);
        return null;
    }



}
