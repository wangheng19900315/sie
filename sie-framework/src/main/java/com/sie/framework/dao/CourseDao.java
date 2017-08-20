package com.sie.framework.dao;

import com.sie.framework.base.GenericDao;
import com.sie.framework.entity.CourseEntity;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface CourseDao extends GenericDao<CourseEntity, Integer> {

    public List<CourseEntity> getList(Integer projectId, Integer systemType);


    public String getNamesByIds(String courseIds);

}
