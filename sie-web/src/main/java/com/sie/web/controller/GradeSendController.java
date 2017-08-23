package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.GradeSendEntity;
import com.sie.service.GradeSendService;
import com.sie.service.StudentService;
import com.sie.service.bean.GradeSendBean;
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
@RequestMapping("/send")
public class GradeSendController {

    private static final Logger LOGGER = Logger.getLogger(GradeSendController.class);

    @Autowired
    private GradeSendService gradeSendService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/send/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/send/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            GradeSendEntity gradeSendEntity = this.gradeSendService.get(id);
            model.addAttribute("entity", JSON.toJSON(gradeSendEntity));
        }
        //添加学生
        Map<Integer,String> students = studentService.getAllStudent();
        model.addAttribute("students", JSON.toJSON(students));
        return "/send/addOrUpdate";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<GradeSendBean> listJons(Integer page, Integer rows ){

        PageInfo<GradeSendBean> pageInfo = null;
        try{
            pageInfo = this.gradeSendService.getGradeSendList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(GradeSendEntity gradeSendEntity){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.gradeSendService.saveOrUpdate(gradeSendEntity);
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
            this.gradeSendService.delete(id);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("删除成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


}