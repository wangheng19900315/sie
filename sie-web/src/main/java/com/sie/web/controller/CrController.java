package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CrEntity;
import com.sie.framework.entity.SchoolEntity;
import com.sie.framework.vo.CrSearchVo;
import com.sie.service.CrService;
import com.sie.service.bean.PageInfo;
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
@RequestMapping("/cr")
public class CrController {

    private static final Logger LOGGER = Logger.getLogger(CrController.class);

    @Autowired
    private CrService crService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/cr/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/cr/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            CrEntity crEntity = this.crService.get(id);
            model.addAttribute("entity", JSON.toJSON(crEntity));
        }

        return "/cr/addOrUpdate";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<CrEntity> listJons(CrSearchVo vo,Integer page, Integer rows ){

        PageInfo<CrEntity> pageInfo = null;
        try{
            pageInfo = this.crService.getList(page,rows, vo.transToHqlOperateVo());
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(CrEntity crEntity){
        ResultBean resultBean = new ResultBean();

        try{
            Integer id = this.crService.saveOrUpdate(crEntity);
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
            this.crService.delete(id);
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