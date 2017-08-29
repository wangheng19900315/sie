package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.CourseEntity;
import com.sie.service.bean.CourseBean;
import com.sie.service.bean.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface CourseService extends BaseService<CourseEntity, Integer> {



    PageInfo<CourseBean> getCourseList(Integer page, Integer rows,  List<HqlOperateVo> hqlOperateVos);


    Integer saveOrUpdate(CourseBean courseBean);

    Map<Integer,String> getCourses(Integer projectId, Integer systemType);

    CourseBean getBean(Integer id);
}
