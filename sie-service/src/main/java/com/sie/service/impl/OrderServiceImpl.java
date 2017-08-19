package com.sie.service.impl;

import com.sie.framework.base.GenericDao;
import com.sie.framework.dao.OrderDao;
import com.sie.framework.dao.RoleDao;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.entity.RoleEntity;
import com.sie.service.OrderService;
import com.sie.service.RoleService;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.PageInfo;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
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

    private OrderDao orderDao;

    @Autowired
    OrderServiceImpl(GenericDao<OrderEntity, Integer> baseDao) {
        super(baseDao);
    }


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

    private void setBeanValues(OrderEntity orderEntity, OrderBean bean){

        try{
            BeanUtils.copyProperties(bean, orderEntity);
//            if(NumberUtil.isSignless(bean.getStatus())){
//
//            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
