package com.sie.service.impl;

import com.sie.framework.base.GenericDao;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.*;
import com.sie.framework.entity.*;
import com.sie.framework.type.*;
import com.sie.service.*;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.OrderImport;
import com.sie.service.vo.CourseVo;
import com.sie.service.vo.OrderVo;
import com.sie.util.DateUtil;
import com.sie.util.NumberUtil;
import com.sie.util.PageUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<OrderEntity,Integer> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    OrderServiceImpl(GenericDao<OrderEntity, Integer> baseDao) {
        super(baseDao);
    }

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private CrDao crDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private OrderPayDao orderPayDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private DormitoryDao dormitoryDao;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private GradeSendService gradeSendService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private PackagePriceService packagePriceService;

    @Autowired
    private StudentService studentService;

    @Override
    public PageInfo<OrderBean> getOrderList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVoList) {


        if (!NumberUtil.isSignless(rows)) {
            rows = Integer.MAX_VALUE;
        }

        if (!NumberUtil.isSignless(page)) {
            page = 0;
        }
        Integer records = orderDao.getCount(hqlOperateVoList);
        PageInfo<OrderBean> pageBean = new PageInfo<>(records, PageUtil.getPageTotal(records, rows));
        pageBean.setPage(PageUtil.getPageNow(page, pageBean.getTotal()));
        Integer firstResult = PageUtil.getFirstResult(pageBean.getPage(), rows);
        Integer maxResults = PageUtil.getMaxResults(rows);
        List<OrderEntity> entityList =  orderDao.getList(hqlOperateVoList,firstResult, maxResults );


        List<OrderBean> orderBeanList = new ArrayList<>();
        if(entityList.size() > 0){
            for(OrderEntity orderEntity:entityList){

                OrderBean bean = new OrderBean();
                setBeanValues(orderEntity, bean);
                orderBeanList.add(bean);
            }
            pageBean.setRows(orderBeanList);
        }

        return pageBean;
    }

    @Override
    public List<OrderBean> getOrderList(List<HqlOperateVo> hqlOperateVoList) {
        List<OrderEntity> entityList = orderDao.getList(hqlOperateVoList);
        List<OrderBean> orderBeanList = new ArrayList<>();
        if(entityList.size() > 0){
            for(OrderEntity entity :entityList){
                OrderBean bean = new OrderBean();
                setBeanValues(entity, bean);
                orderBeanList.add(bean);
            }
        }
        return orderBeanList;
    }

    public OrderBean getDetail(Integer id){
        OrderBean orderBean = null;
        OrderEntity orderEntity = this.orderDao.getEntity(id);
        if(orderEntity != null){
            orderBean = new OrderBean();
            this.setBeanValues(orderEntity, orderBean);

            if(orderEntity.getOrderDetailEntityList().size() > 0){
                List<OrderDetailBean> detailList = new ArrayList<>();
                for(OrderDetailEntity detailEntity:orderEntity.getOrderDetailEntityList()){
                    OrderDetailBean detailBean = new OrderDetailBean();
                    orderDetailService.setDetailBeanValues(orderEntity.getSystemType(),detailEntity, detailBean);
                    detailList.add(detailBean);
                }
                orderBean.setOrderDetailBean(detailList);
            }
        }

        return orderBean;
    }

    @Override
    public OrderBean getAddDetail(Integer id) {
        return null;
    }

    public void setBeanValues(OrderEntity orderEntity, OrderBean bean){
        try{
            ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
            BeanUtils.copyProperties(bean, orderEntity);
            if(NumberUtil.isSignless(bean.getStatus())){
                OrderStatus status = OrderStatus.valueOf(bean.getStatus());
                if(status != null){
                    bean.setStatusName(status.getName());
                }
            }
            if(NumberUtil.isSignless(bean.getPayType())){
                PayType payType = PayType.valueOf(bean.getPayType());
                if(payType != null){
                    bean.setPayTypeName(payType.getName());
                }
            }
            if(orderEntity.getCouponEntity() != null){
                bean.setCouponId(orderEntity.getCouponEntity().getId());
                bean.setCouponName(orderEntity.getCouponEntity().getName());
            }
            if(orderEntity.getCrEntity() != null){
                bean.setCrId(orderEntity.getCrEntity().getId());
                bean.setCrnName(orderEntity.getCrEntity().getPersonName());
            }
            if(orderEntity.getStudentEntity() != null){
                bean.setStudentId(orderEntity.getStudentEntity().getId());
                bean.setStudentName(orderEntity.getStudentEntity().getLastName() + " " + orderEntity.getStudentEntity().getFirstName());
                bean.setStudentTel(orderEntity.getStudentEntity().getTelephone());
                bean.setStudentID(orderEntity.getStudentEntity().getUserID());
                bean.setStudentChineseName(orderEntity.getStudentEntity().getChineseName());
                bean.setSchoolName(orderEntity.getStudentEntity().getSchoolName());
                bean.setProfession(orderEntity.getStudentEntity().getProfession());
                bean.setWeiXin(orderEntity.getStudentEntity().getWeiXin());
                bean.setStudentEmail(orderEntity.getStudentEntity().getEmail());
                //身份证号或者护照号
                if(orderEntity.getStudentEntity().getIdNumber() != null){
                    bean.setIdentity(orderEntity.getStudentEntity().getIdNumber());
                }else{
                    bean.setIdentity(orderEntity.getStudentEntity().getPassportNumber());
                }

            }
            if(NumberUtil.isSignless(orderEntity.getOrderType())){
                OrderType type = OrderType.valueOf(orderEntity.getOrderType());
                if(type != null){
                    bean.setOrderTypeName(type.getName());

                }
            }

            if(NumberUtil.isSignless(orderEntity.getSystemType())){
                SystemType systemType = SystemType.valueOf(orderEntity.getSystemType());
                if(systemType != null){
                    bean.setSystemTypeName(systemType.getName());
                }
            }
            //设置项目名称
            List<String> projectNameList = new ArrayList<>();
            List<String> dormitoryNameList = new ArrayList<>();
            int courseNumber = 0;
            for(OrderDetailEntity detailEntity:orderEntity.getOrderDetailEntityList()){

                if(detailEntity.getDormitoryEntity()== null){
                    //明细为项目
                    projectNameList.add(detailEntity.getProjectEntity().getCode());
                    //课程数进行累加
                    courseNumber = courseNumber + detailEntity.getCourseCount().intValue();
                }else{
                    //明细为住宿
                    dormitoryNameList.add(detailEntity.getDormitoryEntity().getName());
                }
            }
            bean.setProjectNames(StringUtils.join(projectNameList, ","));
            bean.setDormitoryNames(StringUtils.join(dormitoryNameList, ","));
            bean.setCourseNumber(courseNumber);

            for(OrderDetailEntity orderDetailEntity : orderEntity.getOrderDetailEntityList()){
                OrderDetailBean orderDetailBean = new OrderDetailBean();
                this.orderDetailService.setDetailBeanValues(orderEntity.getSystemType(),orderDetailEntity,orderDetailBean);
                bean.getOrderDetailBean().add(orderDetailBean);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //判断现有订单和原来订单的cr和coupon是否相同
    private void updateCrNumber(CrEntity crEntity,CrEntity oldEntity){
        if(crEntity != null ){
            Integer crId = crEntity.getId();
            //现在有cr优惠
            if(oldEntity == null){
                //原来没有优惠活动
                crEntity.setUsed(crEntity.getUsed() + 1);
            }else if(oldEntity.getId() != crId){
                //优惠活动不相同
                oldEntity.setUsed(oldEntity.getUsed() - 1);
                crEntity.setUsed(crEntity.getUsed() + 1);
            }else{
                //优惠活动相同不用进行修改
            }

        }else{
            //本次没有优惠活动
            if(oldEntity != null){
                //原来有优惠活动人数减1
                oldEntity.setUsed(oldEntity.getUsed() - 1);
            }
        }
        //进行保存
        if(crEntity != null){
            crDao.updateEntity(crEntity);
        }
        if(oldEntity != null){
            crDao.updateEntity(oldEntity);
        }

    }
    //判断现有订单和原来订单的cr和coupon是否相同
    private void updateCouponNumber(CouponEntity couponEntity,CouponEntity oldEntity){
        if(couponEntity != null ){
            Integer crId = couponEntity.getId();
            //现在有cr优惠
            if(oldEntity == null){
                //原来没有优惠活动
                couponEntity.setUsed(couponEntity.getUsed() + 1);
            }else if(oldEntity.getId() != crId){
                //优惠活动不相同
                oldEntity.setUsed(oldEntity.getUsed() - 1);
                couponEntity.setUsed(couponEntity.getUsed() + 1);
            }else{
                //优惠活动相同不用进行修改
            }

        }else{
            //本次没有优惠活动
            if(oldEntity != null){
                //原来有优惠活动人数减1
                oldEntity.setUsed(oldEntity.getUsed() - 1);
            }
        }
        //进行保存
        if(couponEntity != null){
            couponDao.updateEntity(couponEntity);
        }
        if(oldEntity != null){
            couponDao.updateEntity(oldEntity);
        }

    }


    public void updateOrderInfo(OrderEntity orderEntity) {
        OrderEntity oldEntity = this.orderDao.getEntity(orderEntity.getId());
        if(oldEntity != null){
            //TODO 修改的时候增加逻辑判断 已提交的订单可以修改为已完成或者已取消
            // 已完成的订单只能修改为申请退款 申请退款的订单只能修改为完成退款
            if(orderEntity.getStatus() != oldEntity.getStatus()){

                if(oldEntity.getStatus() == OrderStatus.CANCEL.value()){
                    if(orderEntity.getStatus() !=  OrderStatus.SUBMIT.value() && orderEntity.getStatus() !=  OrderStatus.APPLY.value()){
                        throw new RuntimeException("已取消的订单，只能修改成已提交或申请退款");
                    }
                }else if(oldEntity.getStatus() == OrderStatus.SUBMIT.value()){
                    if(orderEntity.getStatus() !=  OrderStatus.COMPLETE.value() && orderEntity.getStatus() !=  OrderStatus.CANCEL.value()){
                        throw new RuntimeException("提交的订单，只能修改成已完成或已取消");
                    }
                }else if(oldEntity.getStatus() == OrderStatus.COMPLETE.value()){
                    if(orderEntity.getStatus() !=  OrderStatus.APPLY.value() && orderEntity.getStatus() !=  OrderStatus.SUBMIT.value()){
                        throw new RuntimeException("已完成的订单，只能修改成已提交或申请退款");
                    }

                }else if(oldEntity.getStatus() == OrderStatus.APPLY.value()){
                    if(orderEntity.getStatus() !=  OrderStatus.REFUND.value() && orderEntity.getStatus() !=  OrderStatus.CANCEL.value()){
                        throw new RuntimeException("申请退款中的订单，只能修改成已退款或已取消");
                    }
                }else if(oldEntity.getStatus() == OrderStatus.REFUND.value()){
                     if(orderEntity.getStatus() !=  OrderStatus.APPLY.value()){
                         throw new RuntimeException("已退款的订单，只能修改成申请退款中");
                     }
                }
            }
            //Fixme 修改cr优惠和优惠活动
            updateCrNumber(orderEntity.getCrEntity(),oldEntity.getCrEntity());
            updateCouponNumber(orderEntity.getCouponEntity(),oldEntity.getCouponEntity());


            //修改报名人数
            Integer flag = 0;
            if(orderEntity.getStatus() != oldEntity.getStatus()){
                if(orderEntity.getStatus() == OrderStatus.CANCEL.value()){
                    if(oldEntity.getStatus() == OrderStatus.SUBMIT.value()){
                        flag = -1;
                    }
                }else if(orderEntity.getStatus() == OrderStatus.SUBMIT.value()){
                    if(oldEntity.getStatus() == OrderStatus.CANCEL.value()){
                        flag = 1;
                    }
                }else if(orderEntity.getStatus() == OrderStatus.REFUND.value()){
                    if(oldEntity.getStatus() == OrderStatus.APPLY.value()){
                        flag = -1;
                    }

                }
            }

            oldEntity.setDiscount(orderEntity.getDiscount());
            //cr优惠
            oldEntity.setCrEntity(orderEntity.getCrEntity());
            oldEntity.setCrDiscount(orderEntity.getCrDiscount());
            //优惠券
            oldEntity.setCouponEntity(orderEntity.getCouponEntity());
            oldEntity.setCouponDiscount(orderEntity.getCouponDiscount());

            oldEntity.setPayMoney(orderEntity.getPayMoney());
            oldEntity.setStatus(orderEntity.getStatus());
            oldEntity.setPayType(orderEntity.getPayType());
            oldEntity.setRemark(orderEntity.getRemark());
            oldEntity.setRefundMoney(orderEntity.getRefundMoney());
            oldEntity.setRefundReason(orderEntity.getRefundReason());
            oldEntity.setRefundType(orderEntity.getRefundType());
            oldEntity.setDepositBank(orderEntity.getDepositBank());
            oldEntity.setAccount(orderEntity.getAccount());
            oldEntity.setPayee(orderEntity.getPayee());
            oldEntity.setAlipay(orderEntity.getAlipay());
            oldEntity.setRefundEmail(orderEntity.getRefundEmail());
            this.orderDao.updateEntity(oldEntity);

            if(flag != 0){
                List<OrderDetailEntity> orderDetailEntities = oldEntity.getOrderDetailEntityList();
                for(OrderDetailEntity detailEntity:orderDetailEntities){
                    if(detailEntity.getDormitoryEntity() != null){
                        this.dormitoryService.updateStudentCount(detailEntity.getDormitoryEntity().getId(), oldEntity.getSystemType(),oldEntity.getStudentEntity().getSex(), flag,oldEntity.getOrderType());
                    }else{
                        this.courseService.updateCourseCount(detailEntity.getCourseIds(), oldEntity.getSystemType(),oldEntity.getOrderType(), flag);
                    }
                }

                if(oldEntity.getCouponEntity() != null){
                    CouponEntity couponEntity = this.couponDao.getEntity(oldEntity.getCouponEntity().getId());
                    if(oldEntity.getOrderType() == OrderType.USER.value()){
                        if(!couponService.isAviableUse(couponEntity, flag)){
                            throw new RuntimeException("优惠卷已过期或者已使用完，请核对信息");
                        }
                    }

                    couponEntity.setUsed( couponEntity.getUsed()+flag);
                    int use = couponEntity.getUsed();
                    this.couponDao.updateEntity(couponEntity);
                }

                if(oldEntity.getCrEntity() != null){
                    CrEntity crEntity = oldEntity.getCrEntity();
                    crEntity.setUsed( crEntity.getUsed()+flag);
                    this.crDao.updateEntity(crEntity);
                }

            }


            gradeService.updateStudentGradeList(oldEntity.getStudentEntity().getId());

            gradeSendService.updateStudentGradeSend(oldEntity.getStudentEntity().getId());

            //如果是已经完成的订单修改applicationstep的步骤 3
            Integer step;
            if(orderEntity.getStatus() == OrderStatus.SUBMIT.value()){
                step = 2;
            }else if(orderEntity.getStatus() == OrderStatus.COMPLETE.value()){
                step = 3;
            }else {
                step = null;
            }
            if(step != null){
                ResultBean stepResultBean = studentService.updateApplicationStep(oldEntity.getStudentEntity().getId(),step);
                if(!stepResultBean.isSuccess()){
                    throw new RuntimeException("更新学生申请步骤错误");
                }
            }

        }
    }


    private  ResultBean initOrderBean(OrderBean orderBean, OrderEntity orderEntity){
        ResultBean resultBean = new ResultBean();

        if(orderBean.getOrderDetailBean() == null || orderBean.getOrderDetailBean().size() == 0){
            resultBean.setMessage("订单明细为空，请确认提交信息");
            return resultBean;
        }

        orderEntity.setCode(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        if(NumberUtil.isSignless(orderBean.getOrderType())){
            orderEntity.setOrderType(orderBean.getOrderType());
        }else{
            orderEntity.setOrderType(OrderType.ADMIN.value());
        }

        if(NumberUtil.isSignless(orderBean.getStatus())){
            orderEntity.setStatus(orderBean.getStatus());
        }else{
            orderEntity.setStatus(OrderStatus.SUBMIT.value());
        }

        orderEntity.setSystemType(orderBean.getSystemType());

        if(NumberUtil.isSignless(orderBean.getPayType())){
            orderEntity.setPayType(orderBean.getPayType());
        }else{
            orderEntity.setPayType(PayType.MANUAL.value());
        }

        orderEntity.setMoney(orderBean.getMoney());

        if(NumberUtil.isSignless(orderBean.getDiscount())){
            orderEntity.setDiscount(0.0);
        }else{
            orderEntity.setDiscount(orderBean.getDiscount());
        }

        StudentEntity studentEntity = this.studentDao.getEntity(orderBean.getStudentId());
        if(studentEntity == null){
            resultBean.setMessage("查找不到学生信息，请确认提交信息");
            return resultBean;
        }
        orderEntity.setStudentEntity(studentEntity);

        //设置支付时间
        if(orderBean.getPayTime() != null){
            orderEntity.setPayTime(orderBean.getPayTime());
        }

        if(orderBean.getOrderTime() != null){
            orderEntity.setOrderTime(orderBean.getOrderTime());
        }else{
            orderEntity.setPayTime(orderEntity.getCreateTime());
        }

        if(NumberUtil.isSignless(orderBean.getCouponId())){
            CouponEntity couponEntity = this.couponDao.getEntity(orderBean.getCouponId());
            if(couponEntity == null){
                resultBean.setMessage("查找不到优惠卷信息，请确认提交信息");
                return resultBean;
            }

            orderEntity.setCouponEntity(couponEntity);
            orderEntity.setCouponDiscount(couponEntity.getRmbDiscount());
        }else{
            orderEntity.setCouponEntity(null);
            orderEntity.setCouponDiscount(0.0);
        }

        orderEntity.setCrDiscount(0.0);
        orderEntity.setCrEntity(null);
        //Fixme 是否需要增加cr优惠
        if(NumberUtil.isSignless(orderBean.getCrId())){
            CrEntity crEntity = this.crDao.getEntity(orderBean.getCrId());
            if(crEntity == null){
                resultBean.setMessage("查找不到优惠卷信息，请确认提交信息");
                return resultBean;
            }

            orderEntity.setCrEntity(crEntity);
            orderEntity.setCrDiscount(crEntity.getRmbPrice());
        }else{
            orderEntity.setCrEntity(null);
            orderEntity.setCrDiscount(0.0);
        }



        orderEntity.setRemark(orderBean.getRemark());

        //如果是用户提交，自动计算金额
        if(orderEntity.getOrderType() == OrderType.USER.value()  && orderEntity.getStatus() == OrderStatus.SUBMIT.value()){
            //更新学生的步骤为2
            ResultBean stepResultBean = studentService.updateApplicationStep(studentEntity.getId(),2);
            if(!stepResultBean.isSuccess()){
                return stepResultBean;
            }

            int projectCount = 0;
            int courseCount = 0;
            double money = 0;

            Map<Integer, Integer>  maps = new HashMap<>();

            for(OrderDetailBean orderDetailBean : orderBean.getOrderDetailBean()) {

                if (!NumberUtil.isSignless(orderDetailBean.getDormitoryId())) {
                    if (maps.get(orderDetailBean.getProjectId()) == null) {
                        projectCount++;
                        maps.put(orderDetailBean.getProjectId(), 0);
                    }
                    Integer count = orderDetailBean.getCourseIds().split(",").length;

                    maps.put(orderDetailBean.getProjectId(), maps.get(orderDetailBean.getProjectId()) + count);
                    courseCount += count;
                } else {
                    //住宿
                    DormitoryEntity dormitoryEntity = dormitoryDao.getEntity(orderDetailBean.getDormitoryId());
                    if (dormitoryEntity == null) {
                        resultBean.setMessage("查找不到住宿项目信息，请确认提交信息");
                        return resultBean;
                    }
                    money += dormitoryEntity.getPrice();
                }
            }

            for(Map.Entry<Integer,Integer> entry:maps.entrySet()){
                ProjectEntity projectEntity = this.projectDao.getEntity(entry.getKey());
                if(projectEntity == null){
                    resultBean.setMessage("查找不到项目信息，请确认提交信息");
                    return resultBean;
                }
                Integer totalCourseNumber = 0;
                if(orderBean.getSystemType() == SystemType.SIE.value()){
                    totalCourseNumber = projectEntity.getSieMaxCourse();
                }else{
                    totalCourseNumber = projectEntity.getTruMaxCourse();
                }
                if(entry.getValue() > totalCourseNumber){
                    resultBean.setMessage("项目"+projectEntity.getCode()+"所报的课程数大于规定课程数");
                    return resultBean;
                }
            }

            if(courseCount == 0){
                resultBean.setMessage("订单没有课程数，请确认提交信息");
                return resultBean;
            }

            ProjectPriceEntity priceEntity =  packagePriceService.getEntityByCourse(projectCount, courseCount, orderEntity.getSystemType());
            if(priceEntity == null){
                resultBean.setMessage("查找不到与订单对应的价格信息，请确认提交信息");
                return resultBean;
            }
            money += priceEntity.getRmbPrice();
            orderBean.setMoney(money);

        }



        //Fixme 实际支付金额=总金额-cr优惠金额-优惠活动金额
        if(NumberUtil.isSignless(orderBean.getPayMoney())){
            orderEntity.setPayMoney(orderBean.getPayMoney());
            Double totalMoney = NumberUtil.getDoubleScale(orderEntity.getPayMoney()+orderEntity.getCouponDiscount()+orderEntity.getCrDiscount(),0);
            orderEntity.setMoney(totalMoney);
        }else if(NumberUtil.isSignless(orderBean.getMoney())){
            orderEntity.setMoney(orderBean.getMoney());
            Double payMoney = NumberUtil.getDoubleScale(orderEntity.getMoney()-orderEntity.getCouponDiscount()-orderEntity.getCrDiscount(),0);
            orderEntity.setPayMoney(payMoney);
        }
        resultBean.setSuccess(true);
        return resultBean;
    }

    @Override
    public ResultBean addOrder(OrderBean orderBean) {

        OrderEntity orderEntity = new OrderEntity();

        ResultBean resultBean = initOrderBean(orderBean, orderEntity);
        if(!resultBean.isSuccess()){
            return resultBean;
        }
        resultBean.setSuccess(false);


        this.orderDao.createEntity(orderEntity);

        //创建支付信息
        OrderPayEntity orderPayEntity = new OrderPayEntity();
        orderPayEntity.setOrderEntity(orderEntity);
        orderPayEntity.setPayStatus(PayStatus.SUBMIT.value());
        orderPayEntity.setPayTotal(orderEntity.getPayMoney());
        orderPayEntity.setPayType(orderBean.getPayType());
        this.orderPayDao.createEntity(orderPayEntity);

        //创建订单明细
        for(OrderDetailBean orderDetailBean:orderBean.getOrderDetailBean()){
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrderEntity(orderEntity);

            ProjectEntity projectEntity = this.projectDao.getEntity(orderDetailBean.getProjectId());
            if(projectEntity == null){
//                resultBean.setMessage("查找不到项目信息，请确认提交信息");
                throw new RuntimeException("查找不到项目信息，请确认提交信息");
//                return resultBean;
            }
            orderDetailEntity.setProjectEntity(projectEntity);

            if(NumberUtil.isSignless(orderDetailBean.getDormitoryId())){
                DormitoryEntity dormitoryEntity = this.dormitoryDao.getEntity(orderDetailBean.getDormitoryId());

                if(dormitoryEntity == null){
                    throw new RuntimeException("查找不到宿舍信息，请确认提交信息");
                }
                orderDetailEntity.setDormitoryEntity(dormitoryEntity);
                orderDetailEntity.setCourseCount(0);
            }else{
                orderDetailEntity.setCourseIds(orderDetailBean.getCourseIds());

                if(NumberUtil.isSignless(orderDetailBean.getCourseCount())){
                    orderDetailEntity.setCourseCount(orderDetailBean.getCourseCount());
                }else{
                    orderDetailEntity.setCourseCount(orderDetailBean.getCourseIds().split(",").length);
                }
            }

            this.orderDetailDao.createEntity(orderDetailEntity);
            orderEntity.getOrderDetailEntityList().add(orderDetailEntity);

        }

        Integer flag = 0;

        if(orderEntity.getStatus() == OrderStatus.SUBMIT.value() || orderEntity.getStatus() == OrderStatus.COMPLETE.value()){
           flag = 1;
        }else if(orderEntity.getStatus() == OrderStatus.APPLY.value() || orderEntity.getStatus() == OrderStatus.REFUND.value()){
            flag = -1;
        }

        if(flag != 0){
            orderEntity = this.get(orderEntity.getId());
            List<OrderDetailEntity> orderDetailEntities = orderEntity.getOrderDetailEntityList();
            for(OrderDetailEntity detailEntity:orderDetailEntities){
                if(detailEntity.getDormitoryEntity() != null){
                    this.dormitoryService.updateStudentCount(detailEntity.getDormitoryEntity().getId(), orderEntity.getSystemType(),orderEntity.getStudentEntity().getSex(), flag,orderEntity.getOrderType());
                }else{
                    this.courseService.updateCourseCount(detailEntity.getCourseIds(), orderEntity.getSystemType(),orderEntity.getOrderType(), flag);
                }
            }

            if(orderEntity.getCrEntity() != null){
                orderEntity.getCrEntity().setUsed( orderEntity.getCrEntity().getUsed()+flag);
                this.crDao.updateEntity(orderEntity);
            }

            if(orderEntity.getCouponEntity() != null){
                if(orderEntity.getOrderType() == OrderType.USER.value()){
                    if(!couponService.isAviableUse(orderEntity.getCouponEntity(), flag)){
                        throw new RuntimeException("优惠卷已过期或者已使用完，请核对信息");
                    }
                }

                orderEntity.getCouponEntity().setUsed( orderEntity.getCouponEntity().getUsed()+flag);
                this.couponDao.updateEntity(orderEntity.getCouponEntity());
            }
        }

        gradeService.updateStudentGradeList(orderEntity.getStudentEntity().getId());

        gradeSendService.updateStudentGradeSend(orderEntity.getStudentEntity().getId());

        resultBean.setMessage("添加成功");
        resultBean.setSuccess(true);
        return resultBean;
    }

    public OrderBean excelBeanToOrderBean(List<OrderImport> orderImports,int start,int end) throws Exception{
        String hql = "from StudentEntity where idNumber='" + orderImports.get(start).getStudentID() + "' or passportNumber='" + orderImports.get(start).getStudentID() + "'";
        List<StudentEntity> studentEntities = this.studentDao.getList(hql);
        if (studentEntities.size() == 0) {
            throw new Exception("学生信息不存在");
        }

        //设置orderbean的值
        OrderBean orderBean = new OrderBean();
        orderBean.setStudentId(studentEntities.get(0).getId());

        //订单的来源系统
        String orderSystemTypeName = orderImports.get(start).getSystemTypeName();
        //系统信息
        SystemType systemType = SystemType.valueOfName(orderSystemTypeName);
        if (systemType != null) {
            orderBean.setSystemType(systemType.value());
        }else{
            throw new Exception("下单系统不是SIE或者TRU");
        }

        String payTypeName = orderImports.get(start).getPayTypeName();
        //支付信息
        PayType payType = PayType.valueOfName(payTypeName);
        if (payType != null) {
            orderBean.setPayType(payType.value());
        }
        orderBean.setPayMoney(orderImports.get(start).getPayMoney());
        //TODO 时间合并
        orderBean.setPayTime(new Timestamp(orderImports.get(start).getPayDate().getTime()));
        orderBean.setPayMoney(orderImports.get(start).getPayMoney());


        orderBean.setOrderTime(new Timestamp(orderImports.get(start).getOrderDate().getTime()));
        //订单状态
        OrderStatus orderStatus = OrderStatus.valueOfName(orderImports.get(start).getStatus());
        if (orderStatus != null) {
            orderBean.setStatus(orderStatus.value());
        }

        String crCode = orderImports.get(start).getCr();
        //订单CR推荐码 code进行处理
        if (StringUtils.isNotBlank(crCode)) {
            hql = "from CrEntity where code='" + crCode + "'";
            List<CrEntity> crEntities = crDao.getList(hql);
            if (crEntities.size() != 1) {
                throw new Exception("CR推荐码有误");
            }
            orderBean.setCrId(crEntities.get(0).getId());
        }


        String couponCode = orderImports.get(start).getCoupon();
        //订单优惠码 code进行处理
        if (StringUtils.isNotBlank(crCode)) {
            hql = "from CouponEntity where code='" + couponCode + "'";
            List<CouponEntity> couponEntities = couponDao.getList(hql);
            if (couponEntities.size() != 1) {
                throw new Exception("优惠码有误");
            }
            orderBean.setCouponId(couponEntities.get(0).getId());
        }

        orderBean.setRemark(orderImports.get(start).getRemark() + orderImports.get(start).getPayInfo());

        //设置订单类型
        orderBean.setOrderType(OrderType.ADMIN.value());

        //设置订单明细
        List<OrderDetailBean> orderDetailBean = new ArrayList<>();
        for (int i = start; i < end; i++) {
            OrderDetailBean detailBean = new OrderDetailBean();
            String projectCode = orderImports.get(i).getProjectCode();
            if (orderImports.get(i).getCourseNumber() == 0) {
                //明细为宿舍 得到宿舍Id
                hql = "from DormitoryEntity where code='" + projectCode + "'";
                List<DormitoryEntity> dormitoryEntities = dormitoryDao.getList(hql);
                if (dormitoryEntities.size() != 1) {
                    throw new Exception(orderImports.get(i).getProjectCode() + "宿舍信息有误");
                }
                detailBean.setDormitoryId(dormitoryEntities.get(0).getId());
                detailBean.setProjectId(dormitoryEntities.get(0).getProjectId());
            } else {
                //明细为课程
                hql = "from ProjectEntity where code='" + projectCode + "'";
                List<ProjectEntity> projectEntities = projectDao.getList(hql);
                if (projectEntities.size() != 1) {
                    throw new Exception(orderImports.get(i).getProjectCode() + "项目信息有误");
                }
                detailBean.setProjectId(projectEntities.get(0).getId());
                //处理课程
                String[] courseIDs = orderImports.get(i).getCourseIDs().split(",");
                List<Integer> courseIds = new ArrayList<>();
                for (String courseID : courseIDs) {
                    //查找课程
                    hql = "from CourseEntity where courseID='" + courseID + "'";
                    List<CourseEntity> courseEntities = courseDao.getList(hql);
                    if (courseEntities.size() != 1) {
                        throw new Exception(courseID + "课程不存在 ");
                    }
                    if (!projectEntities.get(0).getId().equals(courseEntities.get(0).getProjectEntity().getId())) {
                        throw new Exception(courseID + "课程不在" + projectCode + "项目下 ");
                    }
                    courseIds.add(courseEntities.get(0).getId());
                }
                detailBean.setCourseIds(StringUtils.join(courseIds, ","));
            }
            orderDetailBean.add(detailBean);

        }
        orderBean.setOrderDetailBean(orderDetailBean);
        return orderBean;
    }


    @Override
    public boolean importBean(List<OrderBean> orderBeanList) {
        for(OrderBean orderBean : orderBeanList){
            try {
                addOrder(orderBean);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public void cancelOrder(Integer mins) {
        List<HqlOperateVo> operateVos = new ArrayList<>();

        String date = DateUtil.format(DateUtil.addMinutes(new Date(), -mins), "yyyy-MM-dd HH:mm:ss");
        operateVos.add(new HqlOperateVo("createTime","<=",date));
        operateVos.add(new HqlOperateVo("status","=",OrderStatus.SUBMIT.value()+""));
        operateVos.add(new HqlOperateVo("orderType","=", OrderType.USER.value()+""));

        List<OrderEntity> orderEntities = this.orderDao.getList(operateVos, 0, 10);
        if(orderEntities != null && orderEntities.size() > 0){
            for(OrderEntity orderEntity:orderEntities){
                orderEntity.setStatus(OrderStatus.CANCEL.value());

                List<OrderDetailEntity> orderDetailEntities = orderEntity.getOrderDetailEntityList();
                for(OrderDetailEntity detailEntity:orderDetailEntities){
                    if(detailEntity.getDormitoryEntity() != null){
                        this.dormitoryService.updateStudentCount(detailEntity.getDormitoryEntity().getId(),  orderEntity.getSystemType(),orderEntity.getStudentEntity().getSex(), -1,orderEntity.getOrderType());
                    }else{
                        this.courseService.updateCourseCount(detailEntity.getCourseIds(), orderEntity.getSystemType(),orderEntity.getOrderType(), -1);
                    }
                }

                gradeService.updateStudentGradeList(orderEntity.getStudentEntity().getId());

                gradeSendService.updateStudentGradeSend(orderEntity.getStudentEntity().getId());
                this.orderDao.updateEntity(orderEntity);
            }
        }
    }


    @Override
    public List<OrderVo> getOrderListVo(String systemType, String studentId,Integer orderStatus, Integer orderId) {
        List<OrderVo> orderVos = new ArrayList<>();
        List<HqlOperateVo> list = new  ArrayList<HqlOperateVo>();
        list.add(new HqlOperateVo("systemType", "=", systemType));
        list.add(new HqlOperateVo("studentEntity.id", "=", studentId));
        if(orderStatus != null && orderStatus > 0){
            list.add(new HqlOperateVo("status", "=", orderStatus.toString()));
        }
        if(orderId != null && orderId > 0){
            list.add(new HqlOperateVo("id", "=", orderId.toString()));
        }
        List<OrderEntity> orderEntities = this.getList(list);
        if(orderEntities.size() > 0) {
            for (OrderEntity orderEntity : orderEntities) {

                OrderBean orderBean = new OrderBean();
                this.setBeanValues(orderEntity, orderBean);
                OrderVo vo = new OrderVo();
                try {
                    BeanUtils.copyProperties(vo,orderBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (orderEntity.getOrderTime() != null) {
                    vo.setOrderTime(DateUtil.format(orderEntity.getOrderTime(), "yyyy-MM-dd"));
                }
                if (orderEntity.getPayTime() != null) {
                    vo.setPayTime(DateUtil.format(orderEntity.getPayTime(), "yyyy-MM-dd"));
                }
                //遍历order的明细
                List<CourseVo> courses = new ArrayList<>();
                for(OrderDetailEntity detailEntity:orderEntity.getOrderDetailEntityList()){
                    ProjectEntity projectEntity = detailEntity.getProjectEntity();
                    if(projectEntity != null){
                        //明细为项目
                        if(vo.getTerm() == null){
                            //设置学期
                            vo.setTerm(DateUtil.format(projectEntity.getStartTime(), "yyyy"));
                        }
                        if(detailEntity.getCourseIds() != null){
                            //得到项目下的课程
                            String[] courseIds = detailEntity.getCourseIds().split(",");
                            for(String courseId : courseIds){
                                CourseVo courseVo = new CourseVo();
                                CourseEntity courseEntity = courseDao.getEntity(Integer.valueOf(courseId));
                                try {
                                    BeanUtils.copyProperties(courseVo,courseEntity);
                                    if(courseEntity.getSchool() != null){
                                        //设置校区名称
                                        School school = School.valueOf(courseEntity.getSchool());
                                        courseVo.setSchoolName(school.getName());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                //设置课程编码
                                SystemType type = SystemType.valueOf(Integer.parseInt(systemType));
                                if(type == SystemType.SIE){
                                    courseVo.setChineseName(courseEntity.getSieChineseName());
                                    courseVo.setEnglishName(courseEntity.getSieEnglishName());
                                    courseVo.setCode(courseEntity.getSieCode());
                                    courseVo.setProjectName(projectEntity.getSieName());
                                }else{
                                    courseVo.setChineseName(courseEntity.getTruChineseName());
                                    courseVo.setEnglishName(courseEntity.getTruEnglishName());
                                    courseVo.setCode(courseEntity.getTruCode());
                                    courseVo.setProjectName(projectEntity.getTruName());
                                }
                                courses.add(courseVo);
                            }
                        }
                    }
                }
                vo.setCourses(courses);
                orderVos.add(vo);
            }
        }
        return orderVos;
    }

    @Override
    public OrderVo getLatestOrderListVo(String systemType, String studentId) {
        OrderVo orderVo = null;
        String hql = "from OrderEntity where hdelete=0 and systemType=" + systemType + " and studentEntity.id=" + studentId + " order by updateTime desc";
        List<OrderEntity> orderEntities = this.getList(hql);
        if(orderEntities.size() > 0) {
            OrderEntity orderEntity = orderEntities.get(0);

            OrderBean orderBean = new OrderBean();
            this.setBeanValues(orderEntity, orderBean);
            orderVo = new OrderVo();
            try {
                BeanUtils.copyProperties(orderVo,orderBean);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (orderEntity.getOrderTime() != null) {
                orderVo.setOrderTime(DateUtil.format(orderEntity.getOrderTime(), "yyyy-MM-dd"));
            }
            if (orderEntity.getPayTime() != null) {
                orderVo.setPayTime(DateUtil.format(orderEntity.getPayTime(), "yyyy-MM-dd"));
            }
            //遍历order的明细
//            List<CourseVo> courses = new ArrayList<>();
//            for(OrderDetailEntity detailEntity:orderEntity.getOrderDetailEntityList()){
//                ProjectEntity projectEntity = detailEntity.getProjectEntity();
//                if(projectEntity != null){
//                    //明细为项目
//                    if(vo.getTerm() == null){
//                        //设置学期
//                        vo.setTerm(DateUtil.format(projectEntity.getStartTime(), "yyyy"));
//                    }
//                    if(detailEntity.getCourseIds() != null){
//                        //得到项目下的课程
//                        String[] courseIds = detailEntity.getCourseIds().split(",");
//                        for(String courseId : courseIds){
//                            CourseVo courseVo = new CourseVo();
//                            CourseEntity courseEntity = courseDao.getEntity(Integer.valueOf(courseId));
//                            try {
//                                BeanUtils.copyProperties(courseVo,courseEntity);
//                                if(courseEntity.getSchool() != null){
//                                    //设置校区名称
//                                    School school = School.valueOf(courseEntity.getSchool());
//                                    courseVo.setSchoolName(school.getName());
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            //设置课程编码
//                            SystemType type = SystemType.valueOf(Integer.parseInt(systemType));
//                            if(type == SystemType.SIE){
//                                courseVo.setCode(courseEntity.getSieCode());
//                            }else{
//                                courseVo.setCode(courseEntity.getTruCode());
//                            }
//                            courses.add(courseVo);
//                        }
//                    }
//                }
//            }
//            vo.setCourses(courses);
        }
        return orderVo;
    }


    @Override
    public OrderPayEntity updatePaymentInfo(Integer orderId,Integer payType) {
        OrderEntity orderEntity = this.orderDao.getEntity(orderId);
        if(orderEntity == null){
            throw new RuntimeException("订单不存在，请检查参数");
        }
        if(orderEntity.getStatus() != OrderStatus.SUBMIT.value()){
            throw new RuntimeException("订单状态不是已提交，请检查参数");
        }

        OrderPayEntity payEntity = orderEntity.getOrderPayEntity();
        payEntity.setPayStatus(PayStatus.SUBMIT.value());
        payEntity.setPayType(payType);
        //微信手续费0.6%
        payEntity.setPayTotal(NumberUtil.getDoubleScale(orderEntity.getPayMoney()*1.006, 2));
        this.orderPayDao.updateEntity(payEntity);

        orderEntity.setPayType(payType);
        this.orderDao.updateEntity(orderEntity);

        return payEntity;
    }

    /**
     * 支付成功
     * @param orderId
     */
    @Override
    public void completePaymentInfo(Integer orderId, Double payTotal) {
        OrderEntity orderEntity = this.orderDao.getEntity(orderId);
        if(orderEntity == null){
            throw new RuntimeException("订单不存在，请检查参数");
        }
        if(orderEntity.getStatus() != OrderStatus.SUBMIT.value()){
            throw new RuntimeException("订单状态不是已提交，请检查参数");
        }
        OrderPayEntity payEntity = orderEntity.getOrderPayEntity();

        if(payTotal > 0 && payEntity.getPayTotal() != payTotal/100){
            throw new RuntimeException("orderId="+orderId+"， 支付金额不一致");
        }

        payEntity.setPayStatus(PayStatus.COMPLETE.value());
        this.orderPayDao.updateEntity(payEntity);
        orderEntity.setStatus(OrderStatus.COMPLETE.value());
        this.updateOrderInfo(orderEntity);
    }
    @Override
    public ResultBean refundOrder(OrderBean orderBean) {
        ResultBean resultBean = new ResultBean();
        OrderEntity oldEntity = this.orderDao.getEntity(orderBean.getId());
        if(oldEntity != null){
            //判断是不是本学生下的单
            if(!oldEntity.getStudentEntity().getId().equals(orderBean.getStudentId())){
                resultBean.setMessage("不是本学生的订单");
                return resultBean;
            }
            //申请退款
            RefundType refundType = RefundType.valueOf(orderBean.getRefundType());
            switch (refundType){
                case BANK:
                    oldEntity.setDepositBank(orderBean.getDepositBank());
                    oldEntity.setAccount(orderBean.getAccount());
                    oldEntity.setPayee(orderBean.getPayee());
                    break;
                case AIPAY:
                    oldEntity.setPayee(orderBean.getPayee());
                    oldEntity.setAlipay(orderBean.getAlipay());
                    break;
                case CANADIANDOLLARS:
                    oldEntity.setRefundEmail(orderBean.getRefundEmail());
                    break;
                case WECHAT:
                    break;
            }
        }
        //设置订单状态为退款
        oldEntity.setStatus(OrderStatus.APPLY.value());
        oldEntity.setRefundMoney(orderBean.getRefundMoney());
        oldEntity.setRefundReason(orderBean.getRefundReason());
        oldEntity.setRefundType(orderBean.getRefundType());
        updateOrderInfo(oldEntity);
        resultBean.setSuccess(true);
        resultBean.setMessage("退款已提交");
        return resultBean;
    }

    @Override
    public void updateOrderInfo(OrderBean orderBean) {
        OrderEntity entity = new OrderEntity();
        try {
            BeanUtils.copyProperties(entity,orderBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置cr优惠实体
        if(NumberUtil.isSignless(orderBean.getCrId())){
            CrEntity crEntity = crDao.getEntity(orderBean.getCrId());
            entity.setCrEntity(crEntity);
            entity.setCrDiscount(crEntity.getRmbPrice());
        }else{
            entity.setCrDiscount(0.0);
        }
        //设置优惠券
        if(NumberUtil.isSignless(orderBean.getCouponId())){
            CouponEntity couponEntity = couponDao.getEntity(orderBean.getCouponId());
            entity.setCouponEntity(couponEntity);
            entity.setCouponDiscount(couponEntity.getRmbDiscount());
        }else{
            entity.setCouponDiscount(0.0);
        }
        updateOrderInfo(entity);
    }

//    private void setOrderEntityValue(OrderEntity entity,OrderBean bean){
//        try {
//            BeanUtils.copyProperties(entity,bean);
//            //设置cr优惠实体
//            if(NumberUtil.isSignless(bean.getCrId())){
//                CrEntity crEntity = crDao.getEntity(bean.getCrId());
//                entity.setCrEntity(crEntity);
//                entity.setCrDiscount(crEntity.getRmbPrice());
//            }
//            //设置优惠券
//            if(NumberUtil.isSignless(bean.getCouponId())){
//                CouponEntity couponEntity = couponDao.getEntity(bean.getCouponId());
//                entity.setCouponEntity(couponEntity);
//                entity.setCouponDiscount(couponEntity.getRmbDiscount());
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
