package com.sie.service;

import com.sie.framework.entity.GradeEntity;
import com.sie.service.bean.GradeBean;
import com.sie.service.bean.PageInfo;


/**
 * Created by wangheng on 2017/8/9.
 */
public interface GradeService extends BaseService<GradeEntity, Integer> {


    public void setDetailBeanValues(GradeEntity gradeEntity, GradeBean bean);

    public PageInfo<GradeBean> getGradeList(Integer page, Integer rows, String  studentName);

    GradeBean getBean(Integer id);

    Integer updateGrade(GradeEntity gradeEntity);
}
