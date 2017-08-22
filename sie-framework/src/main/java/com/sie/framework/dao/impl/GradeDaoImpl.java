package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.CrDao;
import com.sie.framework.dao.GradeDao;
import com.sie.framework.entity.CrEntity;
import com.sie.framework.entity.GradeEntity;
import com.sie.util.StringUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("gradeDao")
public class GradeDaoImpl extends GenericDaoImpl<GradeEntity, Integer> implements GradeDao {

    @Override
    public List<GradeEntity> getList(Integer firstResult, Integer maxResults, String studentName) {

        String hql = "from GradeEntity grade where 1=1 ";

        if(StringUtil.isNotBlank(studentName)){
            hql += " and  grade.studentEntity.userName like '%"+studentName+"%'";
        }
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();
    }

    @Override
    public Integer getCount(String studentName) {
        String hql = "select count(*) from GradeEntity grade where 1=1 ";
        if(StringUtil.isNotBlank(studentName)){
            hql += " and  grade.studentEntity.userName like '%"+studentName+"%'";
        }
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        Long count = (Long)query.uniqueResult();
        return count.intValue();
    }
}
