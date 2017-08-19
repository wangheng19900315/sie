package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.StudentDao;
import com.sie.framework.entity.StudentEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("studentDao")
public class StudentDaoImpl extends GenericDaoImpl<StudentEntity, Integer> implements StudentDao {

}
