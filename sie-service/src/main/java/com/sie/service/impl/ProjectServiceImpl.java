package com.sie.service.impl;

import com.sie.framework.dao.ProjectDao;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.type.SystemType;
import com.sie.service.ProjectService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<ProjectEntity,Integer> implements ProjectService {

    private ProjectDao projectDao;

    @Autowired
    ProjectServiceImpl(ProjectDao projectDao) {
        super(projectDao);
        this.projectDao = projectDao;
    }


    @Override
    public PageInfo<ProjectBean> getProjectList(Integer page, Integer rows, Map<String, Object> parameter){
        PageInfo<ProjectEntity> pageInfo = this.getList(page,rows, parameter);
        PageInfo<ProjectBean> result = new PageInfo<ProjectBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<ProjectBean> projectBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(ProjectEntity projectEntity:pageInfo.getRows()){

                ProjectBean bean = new ProjectBean();
                setBeanValues(projectEntity, bean);
                projectBeanList.add(bean);
            }
            result.setRows(projectBeanList);
        }

        return result;
    }

    private void setBeanValues(ProjectEntity projectEntity, ProjectBean bean){

        try{
            BeanUtils.copyProperties(bean, projectEntity);
            if(NumberUtil.isSignless(bean.getSystem())){
                SystemType systemType = SystemType.valueOf(bean.getSystem());
                if(systemType != null){
                    bean.setSystemName(systemType.getName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
