package com.sie.framework.dao;

import com.sie.framework.base.GenericDao;
import com.sie.framework.entity.GradeSendEntity;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface GradeSendDao extends GenericDao<GradeSendEntity, Integer> {

    GradeSendEntity getRepeatEntity(GradeSendEntity entity);

}
