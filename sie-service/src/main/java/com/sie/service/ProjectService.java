package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.type.SystemType;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.service.vo.ProjectVo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface ProjectService extends BaseService<ProjectEntity, Integer> {



    PageInfo<ProjectBean> getProjectList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVos);
    List<ProjectBean> getProjectList(List<HqlOperateVo> hqlOperateVos);
    Integer saveOrUpdate(ProjectBean projectBean);
    Map<Integer,String> getAllCourseProject(Integer system);
    ProjectBean getBean(Integer id);
    ProjectVo getProjectVoDetail(Integer id,SystemType systemType);
}
