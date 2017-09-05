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

    List<CourseEntity> getCourses(Integer projectId, Integer systemType);

    CourseBean getBean(Integer id);

    /**
     * 更新课程报名人数
     * @param courseIds
     * @param systemType
     * @param orderType
     * @param flag 1表示报名人数加1，-1 表示报名人数减1
     */
    public void updateCourseCount(String courseIds, Integer systemType,  Integer orderType,Integer flag);
}
