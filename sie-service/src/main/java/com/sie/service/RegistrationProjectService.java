package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.RegistrationProjectEntity;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.service.bean.RegistrationProjectBean;
import com.sie.service.vo.ProjectVo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface RegistrationProjectService extends BaseService<RegistrationProjectEntity, Integer> {

    List<RegistrationProjectBean> getTwoProjectCheckbox();
    void updateTwoProjects(List<RegistrationProjectBean> beans);
    List<ProjectVo> getRegistrationProjectVo(String systemType);
}
