package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.TitleContentEntity;
import com.sie.framework.entity.TitleEntity;
import com.sie.service.CouponService;
import com.sie.service.TitleContentService;
import com.sie.service.TitleService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.bean.TreeNode;
import com.sie.util.NumberUtil;
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
@RequestMapping("/truWebEditor")
public class TruWebEditorController {

    private static final Logger LOGGER = Logger.getLogger(TruWebEditorController.class);

    @Autowired
    private TitleContentService titleContentService;

    @Autowired
    private TitleService titleService;


    @RequestMapping("/titleTree.html")
    public String titleTree(Model model){
        List<TreeNode> treeNodes = titleService.getTitleTree();
        model.addAttribute("zNodes", JSON.toJSON(treeNodes));
        return "/truWebEditor/titleTree";
    }

    @RequestMapping("/getTitle.json")
    @ResponseBody
    public ResultBean saveTitleAndContent(Integer id){
        ResultBean resultBean = new ResultBean();
        try{
            TitleEntity titleEntity = titleService.get(id);
            resultBean.setData(titleEntity);
            resultBean.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultBean;
    }

    @RequestMapping("/saveTitleAndContent.json")
    @ResponseBody
    public ResultBean  saveTitleAndContent(){
        ResultBean resultBean = new ResultBean();
        try{
//            Integer id = this.couponService.saveOrUpdate(couponEntity);
//            if(NumberUtil.isSignless(id)){
//                resultBean.setMessage("保存成功");
//                resultBean.setSuccess(true);
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultBean;
    }


}