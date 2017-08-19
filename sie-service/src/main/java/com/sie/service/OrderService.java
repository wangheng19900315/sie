package com.sie.service;

import com.sie.framework.entity.OrderEntity;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.PageInfo;

import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface OrderService extends BaseService<OrderEntity, Integer> {



    PageInfo<OrderBean> getOrderList(Integer page, Integer rows, Map<String, Object> parameter);
}
