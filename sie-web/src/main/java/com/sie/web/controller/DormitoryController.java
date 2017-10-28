package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.DormitoryEntity;
import com.sie.service.DormitoryService;
import com.sie.service.ProjectService;
import com.sie.service.bean.DormitoryBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.StudentDormitoryExport;
import com.sie.util.DateUtil;
import com.sie.util.ExportExcel;
import com.sie.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

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

    //导出住宿信息
    @RequestMapping(value = "export.json")
    @ResponseBody
    public String exportFile(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "住宿信息"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xlsx";
            List<DormitoryBean> dormitoryBeanList = dormitoryService.getDormitoryList(null);
            new ExportExcel(null, DormitoryBean.class).setDataList(dormitoryBeanList).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dormitory/list.html";
    }

    //导出住宿的学生信息
    @RequestMapping(value = "exportStudent.json")
    @ResponseBody
    public String exportStudent(Integer dormitoryId,HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            DormitoryEntity dormitoryEntity = dormitoryService.get(dormitoryId);
            if(dormitoryEntity == null){
                throw new RuntimeException("住宿信息不存在");
            }

            String fileName = dormitoryEntity.getName() + "-学生住宿信息-"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xlsx";
            List<StudentDormitoryExport> students = dormitoryService.getStudentInDormitory(dormitoryId);
            new ExportExcel(null, StudentDormitoryExport.class).setDataList(students).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}