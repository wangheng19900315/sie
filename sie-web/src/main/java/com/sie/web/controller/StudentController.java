package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.StudentEntity;
import com.sie.service.StudentService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.util.FileUtil;
import com.sie.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = Logger.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    /**
     * 作业jar
     */
    @Value("${file.upload.url}")
    private String fileUploadUrl;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/student/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/student/list";
    }

    @RequestMapping("/update.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            StudentEntity studentEntity = this.studentService.get(id);
            model.addAttribute("entity", JSON.toJSON(studentEntity));
        }

        return "/student/update";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<StudentEntity> listJons(Integer page, Integer rows ){

        PageInfo<StudentEntity> pageInfo = null;
        try{
            pageInfo = this.studentService.getList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping( value = "/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(@ModelAttribute StudentEntity studentEntity, @RequestParam("headImage") MultipartFile headImage){
        ResultBean resultBean = new ResultBean();


        try{
            String fileUrl = FileUtil.saveToServer(headImage, fileUploadUrl);
            studentEntity.setImage(fileUrl);
            Integer id = this.studentService.saveOrUpdate(studentEntity);
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