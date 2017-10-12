package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.GradeEntity;
import com.sie.service.bean.GradeBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.vo.GradeVo;

import java.util.List;


/**
 * Created by wangheng on 2017/8/9.
 */
public interface GradeService extends BaseService<GradeEntity, Integer> {


    public void setDetailBeanValues(GradeEntity gradeEntity, GradeBean bean);

    public PageInfo<GradeBean> getGradeList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVoList);

    List<GradeBean> getGradeList(List<HqlOperateVo> hqlOperateVoList);

    GradeBean getBean(Integer id);

    Integer updateGrade(GradeEntity gradeEntity);


    public void updateStudentGradeList(Integer studentId);

    List<GradeVo> getGradeListVo(String systemType, String studentId);
}
