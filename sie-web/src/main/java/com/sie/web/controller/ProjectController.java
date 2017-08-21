package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.service.ProjectService;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.service.bean.ResultBean;
import com.sie.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            System.out.println(JSON.toJSON(bean));
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

}