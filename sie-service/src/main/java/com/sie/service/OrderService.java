package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.vo.OrderSearchVo;
import com.sie.service.bean.GradeSendBean;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.OrderImport;
import com.sie.service.vo.OrderVo;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface OrderService extends BaseService<OrderEntity, Integer> {



    PageInfo<OrderBean> getOrderList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVoList);

    List<OrderBean> getOrderList(List<HqlOperateVo> hqlOperateVoList);

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
     * @return 返回成功的导入的条数 要不都导入成功要不都失败
     */
    boolean importBean(List<OrderBean> orderImports);

    /**
     * 将orderExcel转化成orderBean信息
     * @param orderImports
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    OrderBean excelBeanToOrderBean(List<OrderImport> orderImports,int start,int end) throws Exception;

    /*
     *  * 取消订单
     */
    public void cancelOrder(Integer mins);

    public void setBeanValues(OrderEntity orderEntity, OrderBean bean);

    List<OrderVo> getOrderListVo(String systemType, String studentId,Integer orderStatus);

    OrderVo getLatestOrderListVo(String systemType, String studentId);
}
