package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.LogDao;
import com.sie.framework.entity.LogEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("logDao")
public class LogDaoImpl extends GenericDaoImpl<LogEntity, Integer> implements LogDao {


}
