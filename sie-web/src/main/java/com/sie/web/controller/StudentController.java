package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sie.framework.entity.StudentEntity;
import com.sie.framework.vo.StudentSearchVo;
import com.sie.service.SchoolService;
import com.sie.service.StudentService;
import com.sie.service.bean.GradeSendBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.StudentExcelBean;
import com.sie.util.*;
import org.apache.commons.beanutils.BeanUtils;
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

        PageInfo<StudentEntity> pageInfo = null;
        try{
            pageInfo = this.studentService.getList(page,rows, searchVo.transToHqlOperateVo());
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
            Integer id = this.studentService.saveOrUpdate(studentEntity,1);
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
    @ResponseBody
    public String loadImage(String image, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        if(image != null){
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
        }
        return null;
    }

    //导出学生信息
    @RequestMapping(value = "export.json")
    @ResponseBody
    public String exportFile(StudentSearchVo searchVo,HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "学生信息"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xlsx";
            List<StudentEntity> studentEntities = studentService.getList(searchVo.transToHqlOperateVo());
            List<StudentExcelBean> studentExports = Lists.newArrayList();
            for(StudentEntity studentEntity : studentEntities){
                StudentExcelBean export = new StudentExcelBean();
                BeanUtils.copyProperties(export, studentEntity);
                //设置出生日期
                export.setBirthdayFormate(DateUtil.format(studentEntity.getBirthday(),"yyyy-MM-dd"));
                studentExports.add(export);
            }
            new ExportExcel(null, StudentExcelBean.class).setDataList(studentExports).write(response, fileName).dispose();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/student/list.html";
    }

    @RequestMapping(value = "import.json")
    @ResponseBody
    public ResultBean importFile(@RequestParam("excelFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        ResultBean resultBean = new ResultBean();
        try {
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 0, 0);
            List<StudentExcelBean> list = ei.getDataList(StudentExcelBean.class);
            if(list == null || list.size() == 0){
                resultBean.setMessage("excel数据为空，请检查文件");
                return resultBean;
            }
            for(int i=0; i<list.size(); i++){
                String flag = this.studentService.repeatEntity(list.get(i));
                if(StringUtil.isNotBlank(flag)){
                    failureMsg.append("<p>第"+(i+2)+"行:"+flag+"</p>") ;
                    failureNum ++;
                }
            }

            if(failureNum == 0){
                //没有失败记录
               if(this.studentService.importBean(list)){
                   resultBean.setSuccess(true);
                   resultBean.setMessage("数据导入成功,共"+list.size()+"条");
               }
            }else{
                resultBean.setMessage("数据"+failureNum+"条错误;"+failureMsg.toString());
            }
        } catch (Exception e) {
            resultBean.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return resultBean;
    }

}