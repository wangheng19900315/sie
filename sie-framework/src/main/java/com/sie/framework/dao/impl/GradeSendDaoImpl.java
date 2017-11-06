package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.GradeSendDao;
import com.sie.framework.entity.GradeSendEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("gradeSendDao")
public class GradeSendDaoImpl extends GenericDaoImpl<GradeSendEntity, Integer> implements GradeSendDao {


    @Override
    public GradeSendEntity getRepeatEntity(GradeSendEntity entity) {
        String hql = "from GradeSendEntity where hdelete=0 ";

        hql += " and studentEntity.id='"+entity.getStudentEntity().getId()+"'";
        hql += " and trackingNumber='"+entity.getTrackingNumber()+"'";

        List<GradeSendEntity> list = this.getList(hql);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
