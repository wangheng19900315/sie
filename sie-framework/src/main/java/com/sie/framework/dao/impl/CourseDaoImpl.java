package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.CourseDao;
import com.sie.framework.entity.CourseEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("courseDao")
public class CourseDaoImpl extends GenericDaoImpl<CourseEntity, Integer> implements CourseDao {

}
