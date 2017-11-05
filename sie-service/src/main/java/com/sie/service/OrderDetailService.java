package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.OrderDetailEntity;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.vo.OrderDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface OrderDetailService extends BaseService<OrderDetailEntity, Integer> {




    PageInfo<OrderDetailBean> getOrderDetailList(Integer page, Integer rows, Integer orderId);

    public void setDetailBeanValues(Integer systemType,OrderDetailEntity detailEntity, OrderDetailBean detailBean);

    public void updateCourseIds(OrderDetailEntity detailEntity);

    List<OrderDetailVo> getDetailVoList(String orderId);
}
