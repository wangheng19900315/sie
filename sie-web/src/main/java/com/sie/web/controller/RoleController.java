package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.RoleEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.service.RoleService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.bean.RoleBean;
import com.sie.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private static final Logger LOGGER = Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/user/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/role/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String add(Model model, Integer id){

        if(NumberUtil.isSignless(id)){
            RoleEntity roleEntity = this.roleService.get(id);
            model.addAttribute("roleEntity", JSON.toJSON(roleEntity));
        }
        return "/role/add";
    }


    @RequestMapping(value = "/delete.json")
    @ResponseBody
    public ResultBean delete(Integer id){
        ResultBean resultBean = new ResultBean();

        try{
            this.roleService.delete(id);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("删除成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<RoleBean> listJons(Integer page, Integer rows ){

        PageInfo<RoleBean> pageInfo = null;
        try{
            pageInfo = this.roleService.getRoleList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }

    @RequestMapping(value = "/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(@ModelAttribute RoleEntity userEntity){
        ResultBean resultBean = new ResultBean();

        try{
            Integer id = this.roleService.saveOrUpdate(userEntity);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("保存成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    @RequestMapping("/getSelect.json")
    @ResponseBody
    public String getSelect(){

        String result = "";
        try{
            List<RoleEntity> list = this.roleService.getList();
            if(list != null && list.size() > 0){
                for(RoleEntity roleEntity:list){
                    result += "<option value='"+roleEntity.getId()+"'>"+roleEntity.getName()+"</option>";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


    @RequestMapping(value = "/updateMenu.json")
    @ResponseBody
    public ResultBean updateMenu(Integer id, String menuIds){
        ResultBean resultBean = new ResultBean();

        try{
            this.roleService.updateMenu(id, menuIds);
            resultBean.setMessage("修改成功");
            resultBean.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


}