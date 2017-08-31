package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.SchoolEntity;
import com.sie.framework.entity.StudentEntity;
import com.sie.service.SchoolService;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = Logger.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolService schoolService;

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

    @RequestMapping("/university_list.json")
    @ResponseBody
    public List<String> getUnversities(){
        List<String> schools = schoolService.getAllSchoolName();
        return schools;
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
            studentEntity.setImage(null);
            if(headImage != null && !headImage.isEmpty()){
                String fileUrl = FileUtil.saveToServer(headImage, fileUploadUrl);
                studentEntity.setImage(fileUrl);
            }
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


    //加载头像
    @RequestMapping(value = "/loadImage", method = RequestMethod.GET)
    public String loadImage(String image, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            String imgPath = image;
            ips = new FileInputStream(new File(imgPath));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
        return null;
    }


}