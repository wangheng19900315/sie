package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.OrderDetailEntity;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.vo.OrderSearchVo;
import com.sie.service.OrderDetailService;
import com.sie.service.OrderService;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
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
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = Logger.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/list.html")
    public String list(){
        return "/order/list";
    }


    @RequestMapping("/detail.html")
    public String detail(Model model, Integer id){
        model.addAttribute("id", id);
        return "/order/detail";
    }

    @RequestMapping("/update.html")
    public String update(Model model, Integer id){
        model.addAttribute("id", id);
        return "/order/update";
    }




    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<OrderBean> listJons(OrderSearchVo vo, Integer page, Integer rows){

        PageInfo<OrderBean> pageInfo = null;
        try{
            pageInfo = this.orderService.getOrderList(page,rows, vo);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }


    @RequestMapping("/detail.json")
    @ResponseBody
    public OrderBean detail(Integer orderId){
        OrderBean orderBean = null;
        try{
            orderBean  = this.orderService.getDetail(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderBean;
    }


    @RequestMapping("/add.json")
    @ResponseBody
    public ResultBean add(OrderBean orderBean){

        ResultBean resultBean = new ResultBean();
        try{
            resultBean = this.orderService.addOrder(orderBean);
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    @RequestMapping("/detailList.json")
    @ResponseBody
    public PageInfo<OrderDetailBean> listJons(Integer page, Integer rows,Integer orderId ){

        PageInfo<OrderDetailBean> pageInfo = null;
        try{
            pageInfo = this.orderDetailService.getOrderDetailList(page,rows, orderId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/updateCourseIds.json")
    @ResponseBody
    public ResultBean updateCourseIds(OrderDetailEntity detailEntity){

        ResultBean resultBean = new ResultBean();
        try{
           this.orderDetailService.updateCourseIds(detailEntity);
            resultBean.setSuccess(true);
            resultBean.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    @RequestMapping("/updateOrderInfo.json")
    @ResponseBody
    public ResultBean updateOrderInfo(OrderEntity orderEntity){

        ResultBean resultBean = new ResultBean();
        try{
            this.orderService.updateOrderInfo(orderEntity);
            resultBean.setSuccess(true);
            resultBean.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }




}