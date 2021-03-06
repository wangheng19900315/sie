package com.sie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.*;
import com.sie.framework.type.OrderStatus;
import com.sie.framework.type.OrderType;
import com.sie.framework.type.SystemType;
import com.sie.service.*;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.service.bean.ResultBean;
import com.sie.service.bean.SchoolCategoryBean;
import com.sie.service.vo.*;
import com.sie.util.FileUtil;
import com.sie.util.NumberUtil;
import com.sie.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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

    @Autowired
    private DormitoryService  dormitoryService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private CrService crService;

    @Autowired
    private RegistrationProjectService registrationProjectService;

    @Value("${file.upload.url}")
    private String fileUploadUrl;


    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @RequestMapping("/index.html")
    public String index(){
        return "api";
    }

    private static final String SYSTEM_ACCESS_TOKEN="un23n4no2bu4bs34";

    /**
     *调试过的接口
     */

    //加载头像
    @RequestMapping(value = "/loadImage", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean loadImage(String params, String accessToken, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("loadImage params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();
        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,String > maps = mapper.readValue(params, Map.class);
            String image = maps.get("image");
            ServletOutputStream out = null;
            FileInputStream ips = null;
            if(image != null){
                try {
                    //获取图片存放路径
                    String imgPath = image;
                    ips = new FileInputStream(new File(imgPath));
                    response.setContentType("multipart/form-data");
                    out = response.getOutputStream();
                    //读取文件流
                    int len = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((len = ips.read(buffer)) != -1){
                        out.write(buffer,0,len);
                    }
                    out.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    out.close();
                    ips.close();
                }
            }
            resultBean.setSuccess(true);
            resultBean.setData(null);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getStudnetInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getStudnetInfo(String params, String accessToken){

        logger.info("updateStudent.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,String > maps = mapper.readValue(params, Map.class);
            String studentId = maps.get("studentId");
            if(StringUtil.isBlank(studentId)){
                resultBean.setMessage("studentId 为空，请检查参数");
                return resultBean;
            }
            StudentEntity studentEntity = this.studentService.get(Integer.valueOf(studentId));

            if(studentEntity == null){
                resultBean.setMessage("用户不存在");
                return resultBean;
            }
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            resultBean.setData(studentEntity);

        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;

    }

    /**
     * 学生填报申请单
     * @return
     */
    @RequestMapping(value = "/saveApplicationForm.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  updateStudent(String params, String accessToken,MultipartFile headImage){

        logger.info("saveApplicationForm.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> paramsMap = mapper.readValue(params,Map.class);
            params = mapper.writeValueAsString(paramsMap);
            StudentEntity studentEntity = mapper.readValue(params, StudentEntity.class);

            if(headImage != null && !headImage.isEmpty()){
                String fileUrl = FileUtil.saveToServer(headImage, fileUploadUrl);
                studentEntity.setImage(fileUrl);
            }
            //设置学生的步骤为3
            studentEntity.setApplicationStep(1);

            resultBean = this.studentService.updateEntity(studentEntity,2);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;

    }

    /**
     * 学生填报成绩单寄送地址
     * @return
     */
    @RequestMapping(value = "/saveGradeSend.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  saveGradeSend(String params, String accessToken){

        logger.info("updateStudent.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            StudentEntity studentEntity = mapper.readValue(params, StudentEntity.class);
            //设置学生的步骤为4
            studentEntity.setApplicationStep(4);

            resultBean = this.studentService.updateEntity(studentEntity,3);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;

    }


    /**
     * 获取用户订单
     * @return
     */
    @RequestMapping(value = "/getOrderList.json", method = RequestMethod.POST)
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

            String status = maps.get("orderStatus");
            Integer orderStatus = 0;
            if(StringUtil.isNotBlank(status)){
                orderStatus = Integer.parseInt(status);
            }

            String orderId = maps.get("orderId");
            Integer id = 0;
            if(StringUtil.isNotBlank(orderId)){
                id = Integer.parseInt(orderId);
            }

            List<OrderVo> orderVos = this.orderService.getOrderListVo(systemType, studentId,orderStatus,id);
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            Map<String,List<OrderVo>> orderVoMap = new HashMap<>();
            for(OrderVo orderVo : orderVos){
                if(orderVoMap.get(orderVo.getTerm()) == null){
                    List<OrderVo> vos = new ArrayList<>();
                    vos.add(orderVo);
                    orderVoMap.put(orderVo.getTerm(),vos);
                }else{
                    orderVoMap.get(orderVo.getTerm()).add(orderVo);
                }
            }
            resultBean.setData(orderVoMap);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 获取用户的成绩单
     * @return
     */
    @RequestMapping(value = "/getReportList.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getReportList(String params, String accessToken){
        logger.info("getReportList.json params="+params +" accessToken="+accessToken);
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

            List<GradeVo> gradeVos = this.gradeService.getGradeListVo(systemType, studentId);
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            Map<String,List<GradeVo>> gradeVoMap = new HashMap<>();
            for(GradeVo gradeVo : gradeVos){
                if(gradeVoMap.get(gradeVo.getTerm()) == null){
                    List<GradeVo> vos = new ArrayList<>();
                    vos.add(gradeVo);
                    gradeVoMap.put(gradeVo.getTerm(),vos);
                }else{
                    gradeVoMap.get(gradeVo.getTerm()).add(gradeVo);
                }
            }
            resultBean.setData(gradeVoMap);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 获取报名项目信息
     * @return
     */
    @RequestMapping(value = "/getProjects.json", method = RequestMethod.POST)
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
            SystemType type;
            if(StringUtil.isBlank(systemType)){
                resultBean.setMessage("systemType 为空，请检查参数");
                return resultBean;
            }
            type = SystemType.valueOf(Integer.parseInt(systemType));
            if(type == null){
                resultBean.setMessage("systemType不存在");
                return resultBean;
            }
            List<HqlOperateVo> list = new  ArrayList<HqlOperateVo>();
            list.add(new HqlOperateVo("system", "in", systemType+","+ SystemType.SIEANDTRU.value()));
            list.add(new HqlOperateVo("enabled", "=", "1"));//可用项目信息
            List<ProjectEntity> projectEntities = this.projectService.getList(list);
            List<ProjectVo> projectVos = new ArrayList<>();
            if(projectEntities.size() > 0){

                for(ProjectEntity projectEntity:projectEntities){
                    ProjectVo vo = new ProjectVo();
//                    BeanUtils.copyProperties(projectEntity, vo);
//                    if(projectEntity.getStartTime() != null){
//                        vo.setStartTime(DateUtil.format(projectEntity.getStartTime(), "yyyy-MM-dd"));
//                    }
//                    if(projectEntity.getEndTime() != null){
//                        vo.setEndTime(DateUtil.format(projectEntity.getEndTime(), "yyyy-MM-dd"));
//                    }
                    //根据系统设置项目的名称和最大课程数
                    if(type == SystemType.SIE){
                        //sie系统
                        vo.setName(projectEntity.getSieName());
                        vo.setMaxCourse(projectEntity.getSieMaxCourse());
                    }else{
                        //tru系统
                        vo.setName(projectEntity.getTruName());
                        vo.setMaxCourse(projectEntity.getTruMaxCourse());
                    }
                    //设置项目为单个项目
                    vo.setIds(projectEntity.getId().toString());
                    projectVos.add(vo);
                }

            }

            //组合项目
            List<ProjectVo> registrationVos = registrationProjectService.getRegistrationProjectVo(systemType);
            projectVos.addAll(registrationVos);
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            resultBean.setData(projectVos);

        }catch(Exception e){
            e.printStackTrace();
        }
        return resultBean;
    }

    /**
     * 获取报名项目下的课程和住宿信息
     * @return
     */
    @RequestMapping(value="/getProjectDetails.json" , method = RequestMethod.POST)
    @ResponseBody
    public ResultBean getProjectDetails(String params, String accessToken){
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
            SystemType type;
            if(StringUtil.isBlank(systemType)){
                resultBean.setMessage("systemType 为空，请检查参数");
                return resultBean;
            }
            type = SystemType.valueOf(Integer.parseInt(systemType));
            if(type == null){
                resultBean.setMessage("systemType不存在");
                return resultBean;
            }
            String projectIds = maps.get("projectIds");
            if(StringUtil.isBlank(projectIds)){
                resultBean.setMessage("projectIds 为空，请检查参数");
                return resultBean;
            }
            List<ProjectVo> projectVos = new ArrayList<>();
            String[] ids = projectIds.split(",");
            for(String id : ids){
                //获取项目的信息
                ProjectVo vo = this.projectService.getProjectVoDetail(Integer.parseInt(id),type);
                projectVos.add(vo);
            }
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            resultBean.setData(projectVos);

        }catch(Exception e){
            e.printStackTrace();
        }
        return resultBean;
    }

    /**
     * 得到用户订单金额
     * @return
     */
    @RequestMapping(value = "/getOrderPrice.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getOrderPrice(String params, String accessToken){
        logger.info("getOrderPrice.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            OrderBean orderBean = mapper.readValue(params, OrderBean.class);

            //订单的金额 key 为course的为课程价格 key为total的为总价格 key为dormitory
            OrderPriceVo money = new OrderPriceVo();
            Map<Integer,Double> dormitoryPrice = new HashMap();
            //设置订单金额
            int projectNum = 0;
            int courseNum = 0;
            double totalMoney = 0;
            for(OrderDetailBean detailBean : orderBean.getOrderDetailBean()){
                //添加住宿的价格
                if(detailBean.getDormitoryId() != null){
                    DormitoryEntity dormitoryEntity = dormitoryService.get(detailBean.getDormitoryId());
                    if(dormitoryEntity != null){
                        dormitoryPrice.put(dormitoryEntity.getId(),dormitoryEntity.getPrice());
                        totalMoney += dormitoryEntity.getPrice();
                    }
                }else{
                    projectNum = projectNum + 1;
                    String[] courses = detailBean.getCourseIds().split(",");
                    courseNum += courses.length;
                }
            }
            //添加课程的价格
            money.setCoursePrice(packagePriceService.getProjectPrice(orderBean.getSystemType(),projectNum,courseNum));
            totalMoney += packagePriceService.getProjectPrice(orderBean.getSystemType(),projectNum,courseNum);
            money.setTotalPrice(totalMoney);
            money.setDormitoryPrice(dormitoryPrice);
            resultBean.setSuccess(true);
            resultBean.setMessage("获取价格成功");
            resultBean.setData(money);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 获取CR优惠码的价格
     * @return
     */
    @RequestMapping(value = "/getCrPrice.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getCrPrice(String params, String accessToken){
        logger.info("getCrPrice.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,String>maps = mapper.readValue(params, Map.class);
//            String systemType = maps.get("systemType");
//            SystemType type;
//            if(StringUtil.isBlank(systemType)){
//                resultBean.setMessage("systemType 为空，请检查参数");
//                return resultBean;
//            }
//            type = SystemType.valueOf(Integer.parseInt(systemType));
//            if(type == null){
//                resultBean.setMessage("systemType不存在");
//                return resultBean;
//            }
            String crCode = maps.get("crCode");
            if(StringUtil.isBlank(crCode)){
                resultBean.setMessage("crCode 为空，请检查参数");
                return resultBean;
            }

            CrEntity crEntity = crService.getCrByCode(crCode);
            if(crEntity == null){
                resultBean.setMessage("cr优惠不存在");
                return resultBean;
            }

            resultBean.setSuccess(true);
            resultBean.setMessage("获取Cr价格成功");
            resultBean.setData(crEntity);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 得到课程的价格
     * @return
     */
    @RequestMapping(value = "/getCoursePrice.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getCoursePrice(String params, String accessToken){
        logger.info("getCoursePrice.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> paramsMap = mapper.readValue(params, Map.class);

            Integer systemType = (Integer)paramsMap.get("systemType");
            if(systemType == null){
                resultBean.setMessage("系统参数为空");
                return resultBean;
            }

            Integer projectNumber = (Integer)paramsMap.get("projectNumber");
            if(projectNumber == null){
                resultBean.setMessage("项目数量为空");
                return resultBean;
            }
            Integer courseNumber = (Integer)paramsMap.get("courseNumber");
            if(courseNumber == null){
                resultBean.setMessage("课程数量为空");
                return resultBean;
            }

            double money = packagePriceService.getProjectPrice(systemType,projectNumber,courseNumber);

            resultBean.setSuccess(true);
            resultBean.setMessage("获取价格成功");
            resultBean.setData(money);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 用户下单
     * @return
     */
    @RequestMapping(value = "/createOrder.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  createOrder(String params, String accessToken){
        logger.info("createOrder.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            OrderBean orderBean = mapper.readValue(params, OrderBean.class);
            orderBean.setOrderType(OrderType.USER.value());
            if(!NumberUtil.isSignless(orderBean.getStatus())){
                orderBean.setStatus(OrderStatus.SUBMIT.value());
            }
//            //设置订单金额
//            int projectNum = orderBean.getOrderDetailBean().size();
//            int courseNum = 0;
//            double money = 0;
//            for(OrderDetailBean detailBean : orderBean.getOrderDetailBean()){
//                //添加住宿的价格
//                if(detailBean.getDormitoryId() != null){
//                    DormitoryEntity dormitoryEntity = dormitoryService.get(detailBean.getDormitoryId());
//                    if(dormitoryEntity != null){
//                        money += dormitoryEntity.getPrice();
//                    }
//                }else{
//                    String[] courses = detailBean.getCourseIds().split(",");
//                    courseNum += courses.length;
//                }
//            }
//            //添加课程的价格
//            money += packagePriceService.getProjectPrice(orderBean.getSystemType(),projectNum,courseNum);
//
//            //设置项目的总价格
//            orderBean.setMoney(money);
            resultBean =this.orderService.addOrder(orderBean);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 获得最近的一条订单
     * @return
     */
    @RequestMapping(value = "/getLatestOrderInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getLatestOrderInfo(String params, String accessToken){
        logger.info("getLatestOrderInfo.json params="+params +" accessToken="+accessToken);
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

            OrderVo orderVos = this.orderService.getLatestOrderListVo(systemType, studentId);
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            resultBean.setData(orderVos);
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultBean;
    }

    /**
     * 学生已经完成和已提交的订单的课程信息
     * @return
     */
//    @RequestMapping(value = "/getComAndSubOrderCourse.json", method = RequestMethod.POST)
//    @ResponseBody
//    public ResultBean  getComAndSubOrderCourse(String params, String accessToken){
//        logger.info("getComAndSubOrderCourse.json params="+params +" accessToken="+accessToken);
//        ResultBean resultBean = new ResultBean();
//
//        try{
//            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
//                resultBean.setMessage("token 为空，请检查参数");
//                return resultBean;
//            }
//
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String,String >maps = mapper.readValue(params, Map.class);
//            String studentId = maps.get("studentId");
//            if(StringUtil.isBlank(studentId)){
//                resultBean.setMessage("studentId 为空，请检查参数");
//                return resultBean;
//            }
//            String systemType = maps.get("systemType");
//            if(StringUtil.isBlank(systemType)){
//                resultBean.setMessage("systemType 为空，请检查参数");
//                return resultBean;
//            }
//
//            List<OrderVo> subOrderVos = this.orderService.getOrderListVo(systemType, studentId,OrderStatus.SUBMIT.value(),null);
//            List<OrderVo> comOrderVos = this.orderService.getOrderListVo(systemType, studentId,OrderStatus.COMPLETE.value(),null);
//            subOrderVos.addAll(comOrderVos);
//            List<CourseVo> courseVos = new ArrayList<>();
//            for(OrderVo orderVo : subOrderVos){
//                if(orderVo.getCourses() != null && orderVo.getCourses().size() > 0){
//                    courseVos.addAll(orderVo.getCourses());
//                }
//            }
//            resultBean.setMessage("查找成功");
//            resultBean.setSuccess(true);
//            resultBean.setData(courseVos);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return resultBean;
//    }

    /**
     * 得到学生已完成的订单
     * @return
     */
    @RequestMapping(value = "/getCompleteOrderList.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getCompleteOrderList(String params, String accessToken){
        logger.info("getCompleteOrderList.json params="+params +" accessToken="+accessToken);
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
            List<OrderVo> comOrderVos = this.orderService.getOrderListVo(systemType, studentId,OrderStatus.COMPLETE.value(),null);
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            resultBean.setData(comOrderVos);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    /**
     * 提交退款申请
     * @return
     */
    @RequestMapping(value = "/refundOrder.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  refundOrder(String params, String accessToken){
        logger.info("refundOrder.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            ObjectMapper mapper = new ObjectMapper();
            OrderBean orderBean = mapper.readValue(params, OrderBean.class);
            resultBean = orderService.refundOrder(orderBean);
//            String studentId = maps.get("studentId");
//            if(StringUtil.isBlank(studentId)){
//                resultBean.setMessage("studentId 为空，请检查参数");
//                return resultBean;
//            }
//            String systemType = maps.get("systemType");
//            if(StringUtil.isBlank(systemType)){
//                resultBean.setMessage("systemType 为空，请检查参数");
//                return resultBean;
//            }
//            List<OrderVo> comOrderVos = this.orderService.getOrderListVo(systemType, studentId,OrderStatus.COMPLETE.value());
//            resultBean.setMessage("查找成功");
//            resultBean.setSuccess(true);
//            resultBean.setData(null);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 得到申请流程
     * @return
     */
    @RequestMapping(value = "/getApplicationStep.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getApplicationStep(String params, String accessToken){
        logger.info("getApplicationStep.json params="+params +" accessToken="+accessToken);
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
            StudentEntity studentEntity = studentService.get(Integer.parseInt(studentId));
            if(studentEntity == null){
                resultBean.setMessage("学生信息错误");
                return resultBean;
            }
            resultBean.setSuccess(true);
            resultBean.setMessage("成功");
            Integer applicationStep = studentEntity.getApplicationStep();
            if(applicationStep == null){
                applicationStep = 0;
            }
            resultBean.setData(applicationStep);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    /**
     * 学生注册
     * @return
     */
    @RequestMapping(value = "/register.json",method=RequestMethod.POST)
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
    @RequestMapping(value = "/login.json",method=RequestMethod.POST)
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
     * 学生修改密码
     * @return
     */
    @RequestMapping(value = "/modifyPassword.json",method=RequestMethod.POST)
    @ResponseBody
    public ResultBean modifyPassword(String params, String accessToken){
        logger.info("modifyPassword.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String userName = maps.get("userName");
            String password = maps.get("password");
            String newPassword = maps.get("newPassword");

            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(userName)){
                resultBean.setMessage("用户名 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(password)){
                resultBean.setMessage("密码 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(newPassword)){
                resultBean.setMessage("新密码 为空，请检查参数");
                return resultBean;
            }
            resultBean  = this.studentService.updatePassword(userName, password, newPassword);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }



    /**
     * 学生忘记密码密码
     * @return
     */
    @RequestMapping(value = "/resetPassword.json",method=RequestMethod.POST)
    @ResponseBody
    public ResultBean resetPassword(String params, String accessToken){
        logger.info("resetPassword.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String userName = maps.get("userName");

            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            if(StringUtil.isBlank(userName)){
                resultBean.setMessage("用户名 为空，请检查参数");
                return resultBean;
            }
            resultBean  = this.studentService.updateResetPassword(userName);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }



    /**
     * 获取学校信息
     * @return
     */
    @RequestMapping(value = "/getSchool.json",method=RequestMethod.POST)
    @ResponseBody
    public ResultBean  getSchool(String params, String accessToken){
        logger.info("getSchool.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String >maps = mapper.readValue(params, Map.class);
            String province = maps.get("province");
            List<HqlOperateVo> vos = new ArrayList<HqlOperateVo>();
            if(StringUtil.isNotBlank(province)){
                vos.add(new HqlOperateVo("province", "=", province));
            }
            List<SchoolEntity> schoolEntities = this.schoolService.getList(vos);
            if(schoolEntities.size() > 0){

                List<SchoolVo> schoolVos = new ArrayList<>();
                for(SchoolEntity schoolEntity:schoolEntities){
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
     * 获取学校信息
     * @return
     */
    @RequestMapping(value = "/getSchoolCategory.json",method=RequestMethod.POST)
    @ResponseBody
    public ResultBean  getSchoolCategory(String params, String accessToken){
        logger.info("getSchool.json params="+params +" accessToken="+accessToken);
        ResultBean resultBean = new ResultBean();

        try{
            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
                resultBean.setMessage("token 为空，请检查参数");
                return resultBean;
            }

            List<SchoolCategoryBean> schoolEntities = this.schoolService.getCategory();
            resultBean.setMessage("查找成功");
            resultBean.setSuccess(true);
            resultBean.setData(schoolEntities);

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
     * 获取用户订单详情
     * @return
     */
//    @RequestMapping("/getOrderDetail.json")
//    @ResponseBody
//    public ResultBean  getOrderInfo(String params, String accessToken){
//        logger.info("getOrderDetail.json params="+params +" accessToken="+accessToken);
//        ResultBean resultBean = new ResultBean();
//
//        try{
//            if(StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)){
//                resultBean.setMessage("token 为空，请检查参数");
//                return resultBean;
//            }
//
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String,String >maps = mapper.readValue(params, Map.class);
//            String orderId = maps.get("orderId");
//            if(StringUtil.isBlank(orderId)){
//                resultBean.setMessage("orderId 为空，请检查参数");
//                return resultBean;
//            }
//
//            List<OrderDetailVo> orderDetailVos = this.orderDetailService.getDetailVoList(orderId);
//            resultBean.setMessage("查找成功");
//            resultBean.setSuccess(true);
//            resultBean.setData(orderDetailVos);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return resultBean;
//    }




}
