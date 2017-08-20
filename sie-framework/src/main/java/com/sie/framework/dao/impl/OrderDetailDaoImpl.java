package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.OrderDetailDao;
import com.sie.framework.entity.OrderDetailEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends GenericDaoImpl<OrderDetailEntity, Integer> implements OrderDetailDao {


    @Override
    public List<OrderDetailEntity> getDetailList(Integer firstResult, Integer maxResults, Integer orderId) {
        String hql = "from OrderDetailEntity detail where detail.orderEntity.id="+orderId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();
    }

    @Override
    public Integer getDetailCount(Integer orderId) {
        String hql = "select count(*) from OrderDetailEntity detail where detail.orderEntity.id="+orderId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        Long count = (Long)query.uniqueResult();
        return count.intValue();
    }
}
