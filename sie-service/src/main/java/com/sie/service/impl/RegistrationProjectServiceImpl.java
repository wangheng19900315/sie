package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.RegistrationProjectDao;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.RegistrationProjectEntity;
import com.sie.framework.type.SystemType;
import com.sie.service.RegistrationProjectService;
import com.sie.service.bean.RegistrationProjectBean;
import com.sie.service.vo.ProjectVo;
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
                    if((entity.getProjectOneEntity().getId().equals(projectEntities.get(i).getId()) && entity.getProjectTwoEntity().getId().equals(projectEntities.get(j).getId()))
                    || (entity.getProjectOneEntity().getId().equals(projectEntities.get(j).getId()) && entity.getProjectTwoEntity().getId().equals(projectEntities.get(i).getId()))){
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
    public void updateTwoProjects(List<RegistrationProjectBean> beans) {
        //删除所有数据
        String hql="update RegistrationProjectEntity r set r.hdelete=1";
        registrationProjectDao.updateByHql(hql);

        //存储数据
        //List<RegistrationProjectEntity> entities = new ArrayList<>();
        for(RegistrationProjectBean bean: beans){
            RegistrationProjectEntity entity = new RegistrationProjectEntity();
            entity.setProjectOneEntity(projectDao.getEntity(bean.getProjectOneId()));
            entity.setProjectTwoEntity(projectDao.getEntity(bean.getProjectTwoId()));
            registrationProjectDao.createEntity(entity);
        }
    }

    @Override
    public List<ProjectVo> getRegistrationProjectVo(String systemType) {
        SystemType type = SystemType.valueOf(Integer.parseInt(systemType));
        List<ProjectVo> projectVos = new ArrayList<>();
        List<HqlOperateVo> list = new  ArrayList<HqlOperateVo>();
        //设置项目可用和项目所在系统
        list.add(new HqlOperateVo("projectOneEntity.system", "in", systemType+","+ SystemType.SIEANDTRU.value()));
        list.add(new HqlOperateVo("projectOneEntity.enabled", "=", "1"));//可用项目信息
        list.add(new HqlOperateVo("projectTwoEntity.system", "in", systemType+","+ SystemType.SIEANDTRU.value()));
        list.add(new HqlOperateVo("projectTwoEntity.enabled", "=", "1"));//可用项目信息
        // 得到组合project的数据
        List<RegistrationProjectEntity> registrationProjectEntities = registrationProjectDao.getList(list);
        for(RegistrationProjectEntity registrationProjectEntity : registrationProjectEntities){
            ProjectEntity one = registrationProjectEntity.getProjectOneEntity();
            ProjectEntity two = registrationProjectEntity.getProjectTwoEntity();
            ProjectVo vo = new ProjectVo();
            //项目都可用
            //根据系统设置项目的名称和最大课程数
            if(type == SystemType.SIE){
                //sie系统
                vo.setName(one.getSieName() + "+" + two.getSieName());
                vo.setMaxCourse(one.getSieMaxCourse() + two.getSieMaxCourse());
            }else{
                //tru系统
                vo.setName(one.getTruName() + "+" + two.getTruName());
                vo.setMaxCourse(one.getTruMaxCourse() + two.getTruMaxCourse());
            }
            //设置项目为单个项目
            vo.setIds(one.getId().toString() + "," + two.getId());
            projectVos.add(vo);
        }
        return projectVos;
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
