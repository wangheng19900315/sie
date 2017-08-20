package com.sie.framework.dao;

import com.sie.framework.base.GenericDao;
import com.sie.framework.entity.OrderDetailEntity;
import com.sie.framework.entity.OrderEntity;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface OrderDetailDao extends GenericDao<OrderDetailEntity, Integer> {

    public List<OrderDetailEntity> getDetailList(Integer firstResult, Integer maxResults,Integer orderId);

    public Integer getDetailCount(Integer orderId);
}
