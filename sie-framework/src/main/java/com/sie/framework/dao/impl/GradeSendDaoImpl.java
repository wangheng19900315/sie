package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.GradeSendDao;
import com.sie.framework.entity.GradeSendEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("gradeSendDao")
public class GradeSendDaoImpl extends GenericDaoImpl<GradeSendEntity, Integer> implements GradeSendDao {

}
