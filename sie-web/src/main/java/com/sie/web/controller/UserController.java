package com.sie.web.controller;

import com.sie.framework.entity.UserEntity;
import com.sie.framework.type.Constant;
import com.sie.service.UserService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.util.NumberUtil;
import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.apache.xpath.operations.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/user/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/user/list";
    }

    @RequestMapping("/add.html")
    public String add(){
        return "/user/add";
    }



    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<UserEntity> listJons(Integer page, Integer rows ){

        PageInfo<UserEntity> pageInfo = null;
        try{
            pageInfo = this.userService.getList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping(value = "/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(@ModelAttribute UserEntity userEntity){
        ResultBean resultBean = new ResultBean();

        try{
            Integer id = this.userService.saveOrUpdate(userEntity);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("保存成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }




    @RequestMapping(value = "/login.json")
    @ResponseBody
    public ResultBean login(String userName, String password, HttpServletRequest request){
        ResultBean resultBean = new ResultBean();
        try{
            UserEntity userEntity = this.userService.login(userName,password);
            if(userEntity != null){
                resultBean.setSuccess(true);
                resultBean.setMessage("登录成功");
                HttpSession session = request.getSession();
                session.setAttribute(Constant.SYSTEM_USER_ID,userEntity.getId());
                session.setAttribute(Constant.SYSTEM_USER_NAME_KEY,userEntity.getName());

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    @RequestMapping(value = "/logout.json")
    public String logout(){
        ResultBean resultBean = new ResultBean();

        try{

        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }







}