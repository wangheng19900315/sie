package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.service.ProjectService;
import com.sie.service.bean.*;
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
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    private static final Logger LOGGER = Logger.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;




    @RequestMapping("/list.html")
    public String list(){
        return "/project/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            ProjectBean bean = this.projectService.getBean(id);
            model.addAttribute("entity", JSON.toJSON(bean));
        }
        return "/project/addOrUpdate";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<ProjectBean> listJons(Integer page, Integer rows ){

        PageInfo<ProjectBean> pageInfo = null;
        try{
            pageInfo = this.projectService.getProjectList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }


    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(ProjectBean projectBean){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.projectService.saveOrUpdate(projectBean);
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

        try{
            this.projectService.delete(id);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("删除成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    @RequestMapping(value = "/getAllProject.json")
    @ResponseBody
    public Map<Integer,String> getAllProject(Integer system){
        //添加工程
        Map<Integer,String> projects = projectService.getAllCourseProject(system);
        return projects;
    }

    //导出项目信息
    @RequestMapping(value = "export.json")
    @ResponseBody
    public String exportFile(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "项目信息"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xlsx";
            List<ProjectBean> projectBeanList = projectService.getProjectList(null);
            new ExportExcel(null, ProjectBean.class).setDataList(projectBeanList).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/student/list.html";
    }

}