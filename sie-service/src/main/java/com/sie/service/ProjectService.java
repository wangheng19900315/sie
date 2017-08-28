package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.ProjectEntity;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface ProjectService extends BaseService<ProjectEntity, Integer> {



    PageInfo<ProjectBean> getProjectList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVos);
    Integer saveOrUpdate(ProjectBean projectBean);
    Map<Integer,String> getAllCourseProject();
    ProjectBean getBean(Integer id);
}
