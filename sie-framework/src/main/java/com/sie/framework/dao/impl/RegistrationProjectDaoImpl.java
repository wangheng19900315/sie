package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.RegistrationProjectDao;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.RegistrationProjectEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("registrationProjectDao")
public class RegistrationProjectDaoImpl extends GenericDaoImpl<RegistrationProjectEntity, Integer> implements RegistrationProjectDao {

}
