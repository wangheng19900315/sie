package com.sie.service.impl;

import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.RegistrationProjectDao;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.RegistrationProjectEntity;
import com.sie.service.RegistrationProjectService;
import com.sie.service.bean.RegistrationProjectBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("registrationProjectService")
public class RegistrationProjectServiceImpl extends BaseServiceImpl<RegistrationProjectEntity,Integer> implements RegistrationProjectService {

    @Autowired
    private ProjectDao projectDao;

    private RegistrationProjectDao registrationProjectDao;

    @Autowired
    RegistrationProjectServiceImpl(RegistrationProjectDao registrationProjectDao) {
        super(registrationProjectDao);
        this.registrationProjectDao = registrationProjectDao;
    }


    @Override
    public List<RegistrationProjectBean> getTwoProjectCheckbox() {
        //得到当前可用的所有项目进行两两组合
        String hql = "from ProjectEntity where hdelete =0 and enabled = 1";
        List<ProjectEntity> projectEntities = projectDao.getList(hql);

        List<RegistrationProjectBean> registrationProjectBeans = new ArrayList<>();

        List<RegistrationProjectEntity> entities = registrationProjectDao.getList();

        for(int i = 0; i < projectEntities.size(); i++){
            for(int j = i+1; j < projectEntities.size(); j++){
                RegistrationProjectBean bean = new RegistrationProjectBean();
                bean.setProjectOneId(projectEntities.get(i).getId());
                bean.setProjectTwoId(projectEntities.get(j).getId());
                bean.setProjectsName(projectEntities.get(i).getCode() + "+" + projectEntities.get(j).getCode());
                //TODO 判断这个组合在数据库中是否存在
                for(RegistrationProjectEntity entity : entities){
                    if((entity.getProjectOneId().equals(projectEntities.get(i).getId()) && entity.getProjectTwoId().equals(projectEntities.get(j).getId()))
                    || (entity.getProjectOneId().equals(projectEntities.get(j).getId()) && entity.getProjectTwoId().equals(projectEntities.get(i).getId()))){
                        bean.setChecked(true);
                    }else{
                        bean.setChecked(false);
                    }
                }
                registrationProjectBeans.add(bean);
            }
        }
        return registrationProjectBeans;
    }

    @Override
    public void saveTwoProjects(List<RegistrationProjectBean> beans) {
        //删除所有数据
        String hql="update RegistrationProjectEntity r set r.hdelete=1";
        registrationProjectDao.updateByHql(hql);

        //存储数据
        //List<RegistrationProjectEntity> entities = new ArrayList<>();
        for(RegistrationProjectBean bean: beans){
            RegistrationProjectEntity entity = new RegistrationProjectEntity();
            entity.setProjectOneId(bean.getProjectOneId());
            entity.setProjectTwoId(bean.getProjectTwoId());
            registrationProjectDao.createEntity(entity);
        }
    }

//    private void setBeanValues(RegistrationProjectEntity entity, RegistrationProjectBean bean){
//
//        try{
//            BeanUtils.copyProperties(bean, entity);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}
