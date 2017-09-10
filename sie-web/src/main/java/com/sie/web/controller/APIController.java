package com.sie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.*;
import com.sie.service.*;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.service.bean.ResultBean;
import com.sie.service.vo.*;
import com.sie.util.DateUtil;
import com.sie.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/20.
 */
@Controller
@RequestMapping("/api")
public class APIController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private PackagePriceService packagePriceService;


    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @RequestMapping("/index.html")
    public String index(){
        return "api";
    }

    private static final String SYSTEM_ACCESS_TOKEN="un23n4no2bu4bs34";
    /**
     * 学生注册
     * @return
     */
    @RequestMapping("/register.json")
    @ResponseBody
    public ResultBean register(String params, String accessToken){
        logger.info("register.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String email = maps.get("email");
            String weixin = maps.get("weixin");
            String password = maps.get("password");

            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }
            if(StringUtil.isBlank(password)){
                resultBean.setMessage("密码 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(email) && StringUtil.isBlank(weixin)){
                resultBean.setMessage("用户名 为空，请检查参数");
                return resultBean;
            }


            resultBean = this.studentService.register(email, weixin, password);
        }catch(Exception e){
            e.printStackTrace();
        }


        return resultBean;
    }

    /**
    * 学生登录
    * @return
    */
    @RequestMapping("/login.json")
    @ResponseBody
    public ResultBean login(String params, String accessToken){
        logger.info("login.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String userName = maps.get("userName");
            String password = maps.get("password");

            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(password)){
                resultBean.setMessage("密码 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(userName)){
                resultBean.setMessage("用户名 为空，请检查参数");
                return resultBean;
            }


            StudentEntity studentEntity  = this.studentService.login(userName, password);
            if(studentEntity != null){
                resultBean.setMessage("登录成功");
                resultBean.setSuccess(true);
                resultBean.setData(studentEntity);
            }else{
                resultBean.setMessage("用户名或者密码错误，请检查信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 学生修改信息
     * @return
     */
    @RequestMapping("/updateStudent.json")
    @ResponseBody
    public ResultBean  updateStudent(String params, String accessToken){

        logger.info("updateStudent.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            StudentEntity studentEntity = mapper.readValue(params, StudentEntity.class);


            resultBean = this.studentService.updateEntity(studentEntity);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;

    }

    /**
     * 获取学校信息
     * @return
     */
    @RequestMapping("/getSchool.json")
    @ResponseBody
    public ResultBean  getSchool(String params, String accessToken){
        logger.info("getSchool.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{

            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }


            List<SchoolEntity> studentEntityList = this.schoolService.getList(new ArrayList<HqlOperateVo>());
            if(studentEntityList.size() > 0){

                List<SchoolVo> schoolVos = new ArrayList<>();
                for(SchoolEntity schoolEntity:studentEntityList){
                    SchoolVo vo = new SchoolVo();
                    BeanUtils.copyProperties(schoolEntity, vo);
                    schoolVos.add(vo);
                }
                resultBean.setMessage("查找成功");
                resultBean.setSuccess(true);
                resultBean.setData(schoolVos);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    /**
     * 获取项目信息
     * @return
     */
    @RequestMapping("/getProjects.json")
    @ResponseBody
    public ResultBean  getProjects(String params, String accessToken){
        logger.info("getProjects.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String systemType = maps.get("systemType");
            if(StringUtil.isBlank(systemType)){
                resultBean.setMessage("systemType 为空，请检查参数");
                return resultBean;
            }
            List<HqlOperateVo> list = new  ArrayList<HqlOperateVo>();
            list.add(new HqlOperateVo("system", "=", systemType));
            List<ProjectEntity> projectEntities = this.projectService.getList(list);
            if(projectEntities.size() > 0){

                List<ProjectVo> schoolVos = new ArrayList<>();
                for(ProjectEntity projectEntity:projectEntities){
                    ProjectVo vo = new ProjectVo();
                    BeanUtils.copyProperties(projectEntity, vo);
                    if(projectEntity.getStartTime() != null){
                        vo.setStartTime(DateUtil.format(projectEntity.getStartTime(), "yyyy-MM-dd"));
                    }
                    if(projectEntity.getEndTime() != null){
                        vo.setEndTime(DateUtil.format(projectEntity.getEndTime(), "yyyy-MM-dd"));
                    }
                    schoolVos.add(vo);
                }
                resultBean.setMessage("查找成功");
                resultBean.setSuccess(true);
                resultBean.setData(schoolVos);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return resultBean;
    }


    /**
     * 获取课程信息
     * @return
     */
    @RequestMapping("/getCourses.json")
    @ResponseBody
    public ResultBean  getCourses(String params, String accessToken){
        logger.info("getCourses.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String systemType = maps.get("systemType");
            String projectId = maps.get("projectId");
            if(StringUtil.isBlank(systemType)){
                resultBean.setMessage("systemType 为空，请检查参数");
                return resultBean;
            }
            List<HqlOperateVo> list = new  ArrayList<HqlOperateVo>();
            list.add(new HqlOperateVo("system", "=", systemType));
            list.add(new HqlOperateVo("projectId", "=", projectId));
            List<CourseEntity> courseEntities = this.courseService.getList(list);
            if(courseEntities.size() > 0){

                List<CourseVo> courseVos = new ArrayList<>();
                for(CourseEntity courseEntity:courseEntities){
                    CourseVo vo = new CourseVo();
                    BeanUtils.copyProperties(courseEntity, vo);
                    if(courseEntity.getStartTime() != null){
                        vo.setStartTime(DateUtil.format(courseEntity.getStartTime(), "yyyy-MM-dd"));
                    }
                    if(courseEntity.getEndTime() != null){
                        vo.setEndTime(DateUtil.format(courseEntity.getEndTime(), "yyyy-MM-dd"));
                    }
                    courseVos.add(vo);
                }
                resultBean.setMessage("查找成功");
                resultBean.setSuccess(true);
                resultBean.setData(courseVos);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 获取价格信息
     * @return
     */
    @RequestMapping("/getPrices.json")
    @ResponseBody
    public ResultBean  getPrices(String params, String accessToken){
        logger.info("getPrices.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String systemType = maps.get("systemType");
            if(StringUtil.isBlank(systemType)){
                resultBean.setMessage("systemType 为空，请检查参数");
                return resultBean;
            }
            List<HqlOperateVo> list = new  ArrayList<HqlOperateVo>();
            list.add(new HqlOperateVo("system", "=", systemType));
            List<ProjectPriceEntity> priceEntities = this.packagePriceService.getList(list);
            if(priceEntities.size() > 0){

                List<ProjectPriceVo> projectPriceVos = new ArrayList<>();
                for(ProjectPriceEntity priceEntity:priceEntities){
                    ProjectPriceVo vo = new ProjectPriceVo();
                    BeanUtils.copyProperties(priceEntity, vo);

                    projectPriceVos.add(vo);
                }
                resultBean.setMessage("查找成功");
                resultBean.setSuccess(true);
                resultBean.setData(projectPriceVos);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    /**
     * 获取用户订单
     * @return
     */
    @RequestMapping("/getOrderList.json")
    @ResponseBody
    public ResultBean  getOrderList(String params, String accessToken){
        logger.info("getOrderList.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String studentId = maps.get("studentId");
            if(StringUtil.isBlank(studentId)){
                resultBean.setMessage("studentId 为空，请检查参数");
                return resultBean;
            }
            String systemType = maps.get("systemType");
            if(StringUtil.isBlank(systemType)){
                resultBean.setMessage("systemType 为空，请检查参数");
                return resultBean;
            }

            List<OrderVo> orderVos = this.orderService.getOrderListVo(systemType, studentId);
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            resultBean.setData(orderVos);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 获取用户订单详情
     * @return
     */
    @RequestMapping("/getOrderDetail.json")
    @ResponseBody
    public ResultBean  getOrderInfo(String params, String accessToken){
        logger.info("getOrderDetail.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String orderId = maps.get("orderId");
            if(StringUtil.isBlank(orderId)){
                resultBean.setMessage("orderId 为空，请检查参数");
                return resultBean;
            }

            List<OrderDetailVo> orderDetailVos = this.orderDetailService.getDetailVoList(orderId);
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            resultBean.setData(orderDetailVos);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    /**
     * 用户下单
     * @return
     */
    public ResultBean  createOrder(String params, String accessToken){
        logger.info("getSchool.json params="+params +" accessToken="+accessToken);
        return null;
    }


    /**
     * 用户支付
     * @return
     */
    public ResultBean  payOrder(String params, String accessToken){
        logger.info("getSchool.json params="+params +" accessToken="+accessToken);
        return null;
    }

    /**
     * 完成支付
     * @return
     */
    public ResultBean  paySucess(String params, String accessToken){
        logger.info("getSchool.json params="+params +" accessToken="+accessToken);
        return null;
    }

    /**
     * 用户提交退课
     * @return
     */
    public ResultBean  refundOrder(String params, String accessToken){
        logger.info("refundOrder.json params="+params +" accessToken="+accessToken);
        return null;
    }




}
