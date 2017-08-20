package com.sie.service;

import com.sie.framework.entity.OrderDetailEntity;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.service.bean.PageInfo;

import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface OrderDetailService extends BaseService<OrderDetailEntity, Integer> {




    PageInfo<OrderDetailBean> getOrderDetailList(Integer page, Integer rows, Integer orderId);

    public void setDetailBeanValues(OrderDetailEntity detailEntity, OrderDetailBean detailBean);

    public void updateCourseIds(OrderDetailEntity detailEntity);

}
