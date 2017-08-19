package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.OrderDao;
import com.sie.framework.dao.OrderPayDao;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.entity.OrderPayEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("orderPayDao")
public class OrderPayDaoImpl extends GenericDaoImpl<OrderPayEntity, Integer> implements OrderPayDao {

}
