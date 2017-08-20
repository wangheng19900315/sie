package com.sie.service.impl;

import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.ProjectPriceDao;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.ProjectPriceEntity;
import com.sie.framework.type.SystemType;
import com.sie.service.ProjectService;
import com.sie.service.bean.ProjectPriceBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
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
    private ProjectPriceDao projectPriceDao;

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

    @Override
    public Integer saveOrUpdate(ProjectBean projectBean) {
        if(!validateProjectBean(projectBean)){
            return null;
        }
        if(NumberUtil.isSignless(projectBean.getId())){
            ProjectEntity oldProjectEntity = this.projectDao.getEntity(projectBean.getId());
           //TODO 设置值
            this.projectDao.updateEntity(oldProjectEntity);
            return oldProjectEntity.getId();
        }else{
            ProjectEntity projectEntity = new ProjectEntity();
            try {
                BeanUtils.copyProperties(projectEntity,projectBean);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            this.projectDao.createEntity(projectEntity);
            //TODO 保存价格
            if(NumberUtil.isSignless(projectEntity.getId())){
                saveOrUpdateProjectPrice(projectEntity.getId(),projectBean.getSiePrice(),projectBean.getTruPrice());
            }
            return projectEntity.getId();
        }
    }

    @Override
    public Map<Integer, String> getAllCourseProject() {
        List<ProjectEntity> projectEntities = projectDao.getList();
        Map<Integer,String> projects = new HashedMap();
        //TODO 应该选择可以添加课程的项目
        for(ProjectEntity entity : projectEntities){
            projects.put(entity.getId(),entity.getSieName());
        }
        return projects;
    }

    private boolean validateProjectBean(ProjectBean projectBean){
        //最能报大课程数 = 价格数据进行校验
        if(projectBean.getSiePrice() != null){
            if(!(projectBean.getSieMaxCourse() == projectBean.getSiePrice().length)){
                return false;
            }
        }
        if(projectBean.getTruPrice() != null){
            if(!(projectBean.getTruMaxCourse() == projectBean.getTruPrice().length)){
                return false;
            }
        }

        return true;
    }

    public Integer saveOrUpdatePrice(ProjectPriceBean price) {
        if(NumberUtil.isSignless(price.getId())){
            ProjectPriceEntity oldProjectPriceEntity = this.projectPriceDao.getEntity(price.getId());
            //TODO 设置值
            this.projectDao.updateEntity(oldProjectPriceEntity);
            return oldProjectPriceEntity.getId();
        }else{
            ProjectPriceEntity projectPriceEntity = new ProjectPriceEntity();
            try {
                BeanUtils.copyProperties(projectPriceEntity,price);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            this.projectDao.createEntity(projectPriceEntity);
            return projectPriceEntity.getId();
        }
    }


    private void saveOrUpdateProjectPrice(Integer projectId, ProjectPriceBean[] siePrice, ProjectPriceBean[] truPrice){
        Integer id;
        //保存sie价格
        if(siePrice != null){
            for(int i = 0; i < siePrice.length; i ++){
                ProjectPriceBean priceBean = siePrice[i];
                priceBean.setSystem(1);
                priceBean.setProjectId(projectId);
                saveOrUpdatePrice(priceBean);
            }
        }


        //保存tru价格
        if(truPrice != null){
            for(int i = 0; i < truPrice.length; i ++){
                ProjectPriceBean priceBean = truPrice[i];
                priceBean.setSystem(2);
                priceBean.setProjectId(projectId);
                saveOrUpdatePrice(priceBean);
            }
        }

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
