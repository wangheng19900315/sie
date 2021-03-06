package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.CrEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.service.CouponService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.util.NumberUtil;
import com.sie.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {

    private static final Logger LOGGER = Logger.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/coupon/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/coupon/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            CouponEntity couponEntity = this.couponService.get(id);
            model.addAttribute("entity", JSON.toJSON(couponEntity));
        }

        return "/coupon/addOrUpdate";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<CouponEntity> listJons(String name,Integer page, Integer rows ){

        PageInfo<CouponEntity> pageInfo = null;
        try{
            List<HqlOperateVo> operateVos = new ArrayList<>();
            if(StringUtils.isNotBlank(name)){
                operateVos.add(new HqlOperateVo("name","like",name));
            }
            pageInfo = this.couponService.getList(page,rows, operateVos);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(CouponEntity couponEntity){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.couponService.saveOrUpdate(couponEntity);
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
            this.couponService.delete(id);
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