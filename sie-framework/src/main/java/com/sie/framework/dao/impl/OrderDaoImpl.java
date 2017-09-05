package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.OrderDao;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.vo.OrderSearchVo;
import com.sie.util.NumberUtil;
import com.sie.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("orderDao")
public class OrderDaoImpl extends GenericDaoImpl<OrderEntity, Integer> implements OrderDao {


}
