package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<UserEntity, Integer> implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }



    @Override
    public List<UserEntity> getList(Integer firstResult, Integer maxResults) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct c from UserEntity c ");
        sb.append(" where c.hdelete=0 ");
//        if(StringUtil.isNotBlank(vo.getFilter())){
//            sb.append(" and (c.customerName=:filter or  c.phoneModel=:filter or c.description=:filter)" );
//            params.put("filter", "%"+vo.getFilter()+"%");
//        }
        sb.append(" order by  c.createTime desc");
        Query query = this.sessionFactory.getCurrentSession().createQuery(sb.toString());
//        query.setProperties(params);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();

    }

    @Override
    public Integer getListCount(Integer page, Integer rows) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select count(distinct c) from UserEntity c ");
        sb.append(" where c.hdelete=0 ");
//        if(StringUtil.isNotBlank(vo.getFilter())){
//            sb.append(" and (c.customerName=:filter or  c.phoneModel=:filter or c.description=:filter)" );
//            params.put("filter", "%"+vo.getFilter()+"%");
//        }


        Query query = this.sessionFactory.getCurrentSession().createQuery(sb.toString());
        query.setProperties(params);
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }
}
