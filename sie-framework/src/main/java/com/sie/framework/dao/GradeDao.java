package com.sie.framework.dao;

import com.sie.framework.base.GenericDao;
import com.sie.framework.entity.GradeEntity;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface GradeDao extends GenericDao<GradeEntity, Integer> {
    Integer getCount(String studentName);

    List<GradeEntity> getList(Integer firstResult, Integer maxResults, String studentName);
}
