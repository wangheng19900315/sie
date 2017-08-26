package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.ProjectPriceEntity;
import com.sie.service.PackagePriceService;
import com.sie.service.bean.PackagePriceBean;
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
@RequestMapping("/packagePrice")
public class PackagePriceController {

    private static final Logger LOGGER = Logger.getLogger(PackagePriceController.class);

    @Autowired
    private PackagePriceService packagePriceService;

    @RequestMapping("/list.html")
    public String list(){
        return "/packagePrice/list";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<PackagePriceBean> listJons(Integer page, Integer rows ){

        PageInfo<PackagePriceBean> pageInfo = null;
        try{
            pageInfo = this.packagePriceService.getPriceList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/update.json")
    @ResponseBody
    public ResultBean addOrupdate(ProjectPriceEntity projectPriceEntity){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.packagePriceService.update(projectPriceEntity);
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