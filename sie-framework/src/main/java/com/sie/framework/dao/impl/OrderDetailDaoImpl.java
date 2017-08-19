package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.OrderDetailDao;
import com.sie.framework.entity.OrderDetailEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends GenericDaoImpl<OrderDetailEntity, Integer> implements OrderDetailDao {

}
