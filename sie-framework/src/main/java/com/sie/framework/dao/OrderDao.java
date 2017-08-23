package com.sie.framework.dao;

import com.sie.framework.base.GenericDao;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.vo.OrderSearchVo;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface OrderDao extends GenericDao<OrderEntity, Integer> {


    Integer getCount(OrderSearchVo vo);

    List<OrderEntity> getList(Integer firstResult, Integer maxResults, OrderSearchVo vo);
}
