package com.sie.service.impl;

import com.sie.framework.base.GenericDao;
import com.sie.framework.dao.OrderPayDao;
import com.sie.framework.entity.OrderPayEntity;
import com.sie.service.OrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("orderPayService")
public class OrderPayServiceImpl extends BaseServiceImpl<OrderPayEntity,Integer> implements OrderPayService {

    private OrderPayDao orderPayDao;

    @Autowired
    OrderPayServiceImpl(GenericDao<OrderPayEntity, Integer> orderPayDao) {
        super(orderPayDao);
    }


}
