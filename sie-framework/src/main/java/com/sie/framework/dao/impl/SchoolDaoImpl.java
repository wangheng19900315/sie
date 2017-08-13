package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.SchoolDao;
import com.sie.framework.entity.SchoolEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("schoolDao")
public class SchoolDaoImpl extends GenericDaoImpl<SchoolEntity, Integer> implements SchoolDao {

}
