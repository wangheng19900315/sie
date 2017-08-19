package com.sie.service.impl;

import com.sie.framework.base.GenericDao;
import com.sie.framework.dao.OrderDao;
import com.sie.framework.dao.OrderDetailDao;
import com.sie.framework.entity.OrderDetailEntity;
import com.sie.framework.entity.OrderEntity;
import com.sie.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetailEntity,Integer> implements OrderDetailService {

    private OrderDetailDao orderDetailDao;

    @Autowired
    OrderDetailServiceImpl(GenericDao<OrderDetailEntity, Integer> orderDetailDao) {
        super(orderDetailDao);
    }


}
