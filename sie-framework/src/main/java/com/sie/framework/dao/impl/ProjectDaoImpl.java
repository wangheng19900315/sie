package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.entity.ProjectEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("projectDao")
public class ProjectDaoImpl extends GenericDaoImpl<ProjectEntity, Integer> implements ProjectDao {

}
