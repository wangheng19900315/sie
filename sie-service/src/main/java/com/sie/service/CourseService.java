package com.sie.service;

import com.sie.framework.entity.CourseEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.service.bean.CourseBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;

import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface CourseService extends BaseService<CourseEntity, Integer> {



    PageInfo<CourseBean> getCourseList(Integer page, Integer rows, Map<String, Object> parameter);
//    Integer saveOrUpdate(CourseBean courseBean);
}
