package com.sie.web.controller;

import com.sie.framework.entity.RoleEntity;
import com.sie.service.RoleService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.bean.RoleBean;
import com.sie.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/add.html")
    public String add(){
        return "/role/add";
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