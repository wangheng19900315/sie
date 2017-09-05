package com.sie.service;

import com.sie.framework.entity.OrderEntity;
import com.sie.framework.vo.OrderSearchVo;
import com.sie.service.bean.GradeSendBean;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.OrderImport;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface OrderService extends BaseService<OrderEntity, Integer> {



    PageInfo<OrderBean> getOrderList(Integer page, Integer rows, OrderSearchVo vo);

    public OrderBean getDetail(Integer id);

    /**
     * 获取可以进行加课的订单明细
     * @param id
     * @return
     */
    public OrderBean getAddDetail(Integer id);

    void updateOrderInfo(OrderEntity orderEntity);

    /**
     * 添加订单
     * @param orderBean
     * @return
     */
    ResultBean addOrder(OrderBean orderBean);

    /**
     * 导入订单
     * @param orderImports
     * @param start
     * @param end
     * @return
     */
    String importBean(List<OrderImport> orderImports,int start,int end);
}
