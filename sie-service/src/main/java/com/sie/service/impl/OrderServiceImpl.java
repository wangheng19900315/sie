package com.sie.service.impl;

import com.sie.framework.base.GenericDao;
import com.sie.framework.dao.CouponDao;
import com.sie.framework.dao.CourseDao;
import com.sie.framework.dao.OrderDao;
import com.sie.framework.dao.RoleDao;
import com.sie.framework.entity.OrderDetailEntity;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.entity.RoleEntity;
import com.sie.framework.type.OrderDetailStatus;
import com.sie.framework.type.OrderStatus;
import com.sie.framework.type.OrderType;
import com.sie.framework.type.SystemType;
import com.sie.service.OrderDetailService;
import com.sie.service.OrderService;
import com.sie.service.RoleService;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.service.bean.PageInfo;
import com.sie.util.NumberUtil;
import com.sie.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    @Override
    public PageInfo<OrderBean> getOrderList(Integer page, Integer rows, Map<String, Object> parameter) {

        PageInfo<OrderEntity> pageInfo = this.getList(page,rows, parameter);
        PageInfo<OrderBean> result = new PageInfo<OrderBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<OrderBean> orderBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(OrderEntity orderEntity:pageInfo.getRows()){

                OrderBean bean = new OrderBean();
                setBeanValues(orderEntity, bean);
                orderBeanList.add(bean);
            }
            result.setRows(orderBeanList);
        }

        return result;
    }

    public OrderBean getDetail(Integer id){
        OrderBean orderBean = null;
        OrderEntity orderEntity = this.orderDao.getEntity(id);
        if(orderEntity != null){
            orderBean = new OrderBean();
            this.setBeanValues(orderEntity, orderBean);

            if(orderEntity.getOrderDetailEntityList().size() > 0){
                for(OrderDetailEntity detailEntity:orderEntity.getOrderDetailEntityList()){
                    OrderDetailBean detailBean = new OrderDetailBean();
                    orderDetailService.setDetailBeanValues(detailEntity, detailBean);
                    orderBean.getOrderDetailBeen().add(detailBean);
                }
            }
        }

        return orderBean;
    }

    private void setBeanValues(OrderEntity orderEntity, OrderBean bean){
        try{
            BeanUtils.copyProperties(bean, orderEntity);
            if(NumberUtil.isSignless(bean.getStatus())){
                OrderStatus status = OrderStatus.valueOf(bean.getStatus());
                if(status != null){
                    bean.setStatusName(status.getName());
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
                bean.setStudentName(orderEntity.getStudentEntity().getChineseName());
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

        }catch (Exception e){
            e.printStackTrace();
        }


    }




}
