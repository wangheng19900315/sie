package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.OrderDao;
import com.sie.framework.entity.OrderEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("orderDao")
public class OrderDaoImpl extends GenericDaoImpl<OrderEntity, Integer> implements OrderDao {

}
