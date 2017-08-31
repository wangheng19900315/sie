package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.SchoolEntity;
import com.sie.framework.entity.StudentEntity;
import com.sie.framework.vo.StudentSearchVo;
import com.sie.service.SchoolService;
import com.sie.service.StudentService;
import com.sie.service.bean.GradeBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.util.DateUtil;
import com.sie.util.ExportExcel;
import com.sie.util.FileUtil;
import com.sie.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    public PageInfo<StudentEntity> listJons(StudentSearchVo searchVo,Integer page, Integer rows ){

        //组装查询条件
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtils.isNotBlank(searchVo.getChineseName())){
            operateVos.add(new HqlOperateVo("chineseName","like",searchVo.getChineseName()));
        }
        if(StringUtils.isNotBlank(searchVo.getUserID())){
            operateVos.add(new HqlOperateVo("userID","like",searchVo.getUserID()));
        }
        if(StringUtils.isNotBlank(searchVo.getUserName())){
            operateVos.add(new HqlOperateVo("userName","like",searchVo.getUserName()));
        }

        PageInfo<StudentEntity> pageInfo = null;
        try{
            pageInfo = this.studentService.getList(page,rows, operateVos);
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

    //导出学生信息
    @RequestMapping(value = "export.json")
    public String exportFile(StudentSearchVo searchVo,HttpServletResponse response, RedirectAttributes redirectAttributes) {
        //组装查询条件
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtils.isNotBlank(searchVo.getChineseName())){
            operateVos.add(new HqlOperateVo("chineseName","like",searchVo.getChineseName()));
        }
        if(StringUtils.isNotBlank(searchVo.getUserID())){
            operateVos.add(new HqlOperateVo("userID","like",searchVo.getUserID()));
        }
        if(StringUtils.isNotBlank(searchVo.getUserName())){
            operateVos.add(new HqlOperateVo("userName","like",searchVo.getUserName()));
        }
        try {
            String fileName = "学生信息"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xlsx";
            List<StudentEntity> studentEntities = studentService.getList(operateVos);
            new ExportExcel(null, StudentEntity.class).setDataList(studentEntities).write(response, fileName).dispose();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/student/list.html";
    }


}