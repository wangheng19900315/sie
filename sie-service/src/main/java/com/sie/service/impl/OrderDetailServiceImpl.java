package com.sie.service.impl;

import com.sie.framework.base.GenericDao;
import com.sie.framework.dao.CourseDao;
import com.sie.framework.dao.OrderDao;
import com.sie.framework.dao.OrderDetailDao;
import com.sie.framework.entity.OrderDetailEntity;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.type.OrderDetailStatus;
import com.sie.framework.type.OrderStatus;
import com.sie.service.CourseService;
import com.sie.service.GradeService;
import com.sie.service.OrderDetailService;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.service.bean.PageInfo;
import com.sie.util.NumberUtil;
import com.sie.util.PageUtil;
import com.sie.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetailEntity,Integer> implements OrderDetailService {


    @Autowired
    private GradeService gradeService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private CourseService courseService;

    @Autowired
    OrderDetailServiceImpl(GenericDao<OrderDetailEntity, Integer> orderDetailDao) {
        super(orderDetailDao);
    }


    @Autowired
    private CourseDao courseDao;


    @Override
    public PageInfo<OrderDetailBean> getOrderDetailList(Integer page, Integer rows, Integer orderId) {

        if (!NumberUtil.isSignless(rows)) {
            rows = Integer.MAX_VALUE;
        }

        if (!NumberUtil.isSignless(page)) {
            page = 0;
        }
        Integer records = orderDetailDao.getDetailCount(orderId);
        PageInfo<OrderDetailBean> pageBean = new PageInfo<>(records, PageUtil.getPageTotal(records, rows));
        pageBean.setPage(PageUtil.getPageNow(page, pageBean.getTotal()));
        Integer firstResult = PageUtil.getFirstResult(pageBean.getPage(), rows);
        Integer maxResults = PageUtil.getMaxResults(rows);
        List<OrderDetailEntity> entityList =  orderDetailDao.getDetailList(firstResult, maxResults, orderId);

        List<OrderDetailBean> orderBeanList = new ArrayList<>();
        if(entityList.size() > 0){
            for(OrderDetailEntity detailEntity:entityList){
                OrderDetailBean detailBean = new OrderDetailBean();
                setDetailBeanValues(detailEntity, detailBean);
                orderBeanList.add(detailBean);
            }
            pageBean.setRows(orderBeanList);
        }

        pageBean.setPage(page);
        return pageBean;




    }

    public void setDetailBeanValues(OrderDetailEntity detailEntity, OrderDetailBean detailBean){
        try{
            BeanUtils.copyProperties(detailBean, detailEntity);
            if(detailEntity.getDormitoryEntity() != null){
                detailBean.setDormitoryId(detailEntity.getDormitoryEntity().getId());
                detailBean.setDormitoryName(detailEntity.getDormitoryEntity().getCode());
            }
            if(detailEntity.getProjectEntity() != null){
                detailBean.setProjectId(detailEntity.getProjectEntity().getId());
                detailBean.setProjectName(detailEntity.getProjectEntity().getSieName());
            }
            if(NumberUtil.isSignless(detailEntity.getOrderDetailStatus())){
                OrderDetailStatus status = OrderDetailStatus.valueOf(detailEntity.getOrderDetailStatus());
                if(status != null){
                    detailBean.setOrderDetailStatusName(status.getName());
                }
            }

            if(StringUtil.isNotBlank(detailEntity.getCourseIds())){
                detailBean.setCusterNames(this.courseDao.getNamesByIds(detailEntity.getCourseIds()));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void updateCourseIds(OrderDetailEntity detailEntity) {
        OrderDetailEntity oldEntity = this.orderDetailDao.getEntity(detailEntity.getId());
        if(oldEntity != null){
            if(oldEntity.getOrderEntity().getStatus() != OrderStatus.SUBMIT.value() && oldEntity.getOrderEntity().getStatus() != OrderStatus.COMPLETE.value()){
                throw new RuntimeException("只有已提交或者已完成的订单，才能修改课程");
            }

            this.courseService.updateCourseCount(oldEntity.getCourseIds(), oldEntity.getOrderEntity().getSystemType(),oldEntity.getOrderEntity().getOrderType(), -1);
            this.courseService.updateCourseCount(detailEntity.getCourseIds(), oldEntity.getOrderEntity().getSystemType(),oldEntity.getOrderEntity().getOrderType(), 1);
            oldEntity.setCourseIds(detailEntity.getCourseIds());
            this.orderDetailDao.updateEntity(oldEntity);

            gradeService.updateStudentGradeList(oldEntity.getOrderEntity().getStudentEntity().getId());
        }

    }
}
