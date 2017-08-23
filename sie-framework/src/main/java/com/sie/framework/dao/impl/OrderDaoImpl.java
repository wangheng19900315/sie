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


    @Override
    public Integer getCount(OrderSearchVo vo) {
        StringBuilder sb  = new StringBuilder();
        sb.append("select count(*) from OrderEntity o where o.hdelete=0 ");

        sb.append(appendHql(vo));
        return this.getList_count(sb.toString());
    }

    @Override
    public List<OrderEntity> getList(Integer firstResult, Integer maxResults, OrderSearchVo vo) {
        StringBuilder sb  = new StringBuilder();
        sb.append("from OrderEntity o where o.hdelete=0 ");

        sb.append(appendHql(vo));
        return this.getList(sb.toString(), firstResult, maxResults);

    }

    private String  appendHql(OrderSearchVo vo){
        StringBuilder sb  = new StringBuilder();
        if(StringUtil.isNotBlank(vo.getCouponCode())){
            sb.append(" and o.couponEntity.code like '%"+vo.getCouponCode()+"%'");
        }
        if(StringUtil.isNotBlank(vo.getCrCode())){
            sb.append(" and o.crEntity.code like '%"+vo.getCrCode()+"%'");
        }
        if(StringUtil.isNotBlank(vo.getOrderCode())){
            sb.append(" and o.code like '%"+vo.getOrderCode()+"%'");
        }
        if(StringUtil.isNotBlank(vo.getStudentName())){
            sb.append(" and o.studentEntity.chineseName like '%"+vo.getStudentName()+"%'");
        }
        if(NumberUtil.isSignless(vo.getOrderStatus())){
            sb.append(" and o.status="+vo.getOrderStatus());
        }
        if(NumberUtil.isSignless(vo.getSystemType())){
            sb.append(" and o.systemType="+vo.getSystemType());
        }
        return sb.toString();
    }
}
