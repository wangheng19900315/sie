package com.sie.web.controller;

import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.service.CouponService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/add.html")
    public String add(){
        return "/coupon/add";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<CouponEntity> listJons(Integer page, Integer rows ){

        PageInfo<CouponEntity> pageInfo = null;
        try{
            pageInfo = this.couponService.getList(page,rows, null);
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
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }




}