package com.sie.service.impl;

import com.sie.framework.dao.CourseDao;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.ProjectPriceDao;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.ProjectPriceEntity;
import com.sie.framework.type.SystemType;
import com.sie.service.ProjectService;
import com.sie.service.bean.ProjectPriceBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.util.DateUtil;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<ProjectEntity,Integer> implements ProjectService {

    private ProjectDao projectDao;

    private static final String formString = "yyyy-MM-dd";
    private static final SimpleDateFormat format = new SimpleDateFormat(formString);

    @Autowired
    private ProjectPriceDao projectPriceDao;

    @Autowired
    private CourseDao courseDao;

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
        genarateEntity(projectBean);
        if(!validatorBean(projectBean)){
            return null;
        }
        Date date;
        //将bean转化为entity
        ProjectEntity projectEntity = new ProjectEntity();
        try {
            BeanUtils.copyProperties(projectEntity,projectBean);
            //设置时间格式
            date = DateUtil.parse(projectBean.getStartTimeFormat(),formString);
            projectEntity.setStartTime(new Timestamp(date.getTime()));
            date = DateUtil.parse(projectBean.getEndTimeFormat(),formString);
            projectEntity.setEndTime(new Timestamp(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if(NumberUtil.isSignless(projectEntity.getId())){
            ProjectEntity oldProjectEntity = this.projectDao.getEntity(projectBean.getId());
           //TODO 设置值
            oldProjectEntity.setSystem(projectEntity.getSystem());
            oldProjectEntity.setStartTime(projectEntity.getStartTime());
            oldProjectEntity.setEndTime(projectEntity.getEndTime());
            oldProjectEntity.setSieName(projectEntity.getSieName());
            oldProjectEntity.setSieMaxCourse(projectEntity.getSieMaxCourse());
            oldProjectEntity.setTruName(projectEntity.getTruName());
            oldProjectEntity.setTruMaxCourse(projectEntity.getTruMaxCourse());
            this.projectDao.updateEntity(oldProjectEntity);
//            return oldProjectEntity.getId();
        }else{
            this.projectDao.createEntity(projectEntity);
        }
        //TODO 保存价格
        if(NumberUtil.isSignless(projectEntity.getId())){
            saveOrUpdateProjectPrice(projectEntity.getId(),projectBean);
        }
        return projectEntity.getId();
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

    @Override
    public ProjectBean getBean(Integer id) {
        ProjectEntity entity = projectDao.getEntity(id);
        ProjectBean bean = new ProjectBean();
        try {
            setBeanValues(entity,bean);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        ProjectPriceBean[] siePrice = new ProjectPriceBean[5];
        ProjectPriceBean[] truPrice = new ProjectPriceBean[5];
        //设置price
        String hql = "from ProjectPriceEntity where system=1 and projectId='" + id + "' and hdelete=0 order by courseNumber";
        List<ProjectPriceEntity> siePrices = projectPriceDao.getList(hql);
        for(int i = 0; i < siePrices.size(); i++){
            ProjectPriceBean priceBean = new ProjectPriceBean();
            entityToBean(siePrices.get(i),priceBean);
            siePrice[i] = priceBean;
        }

        hql = "from ProjectPriceEntity where system=2 and projectId='" + id + "' and hdelete=0 order by courseNumber";
        List<ProjectPriceEntity> truPrices = projectPriceDao.getList(hql);
        for(int i = 0; i < truPrices.size(); i++){
            ProjectPriceBean priceBean = new ProjectPriceBean();
            entityToBean(truPrices.get(i),priceBean);
            truPrice[i] = priceBean;
        }
        bean.setSiePrice(siePrice);
        bean.setTruPrice(truPrice);
        return bean;
    }

    private void entityToBean(ProjectPriceEntity entity,ProjectPriceBean bean){
        try {
            BeanUtils.copyProperties(bean, entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer savePrice(ProjectPriceBean price) {
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

    private boolean validatorBean(ProjectBean bean){
        if((bean.getSieMaxCourse() != null) && (bean.getSieMaxCourse() > bean.getSiePrice().length)){
            return false;
        }
        if((bean.getTruMaxCourse() != null) && (bean.getTruMaxCourse() > bean.getTruPrice().length)){
            return false;
        }
        return true;
    }


    private void saveOrUpdateProjectPrice(Integer projectId, ProjectBean bean){
        //删除project下边的price
        String hql = "update ProjectPriceEntity price set price.hdelete=1 where price.projectId="+projectId;
        projectPriceDao.updateByHql(hql);
        
        ProjectPriceBean[] siePrice = bean.getSiePrice();
        ProjectPriceBean[] truPrice = bean.getTruPrice();
        //保存sie价格
        if(bean.getSieMaxCourse() != null){
            for(int i = 0; i < siePrice.length && i < bean.getSieMaxCourse(); i ++){
                ProjectPriceBean priceBean = siePrice[i];
                priceBean.setSystem(1);
                priceBean.setProjectId(projectId);
                savePrice(priceBean);
            }
        }

        //保存tru价格
        if(bean.getTruMaxCourse() != null){
            for(int i = 0; i < truPrice.length && i < bean.getTruMaxCourse(); i ++){
                ProjectPriceBean priceBean = truPrice[i];
                priceBean.setSystem(2);
                priceBean.setProjectId(projectId);
                savePrice(priceBean);
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
            //设置时间格式的字符串
            if(bean.getStartTime() != null){
                bean.setStartTimeFormat(format.format(bean.getStartTime()));
            }
            if(bean.getEndTime() != null){
                bean.setEndTimeFormat(format.format(bean.getEndTime()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //根据选择的所属系统设置entity的值
    private void genarateEntity(ProjectBean projectBean){
        SystemType systemType = SystemType.valueOf(projectBean.getSystem());
        switch (systemType){
            case SIE:
                //将TRU的信息设置为空
                projectBean.setTruMaxCourse(null);
                projectBean.setTruName(null);
                break;
            case TRU:
                //将SIE的信息设置为空
                projectBean.setSieMaxCourse(null);
                projectBean.setSieName(null);
                break;
            case SIEANDTRU:
                break;
            default:
                projectBean = null;
        }
    }

    @Override
    public void delete(Integer id) {
        //删除project
        projectDao.deleteEntity(projectDao.getEntity(id));
        //删除项目下的价格
        String hql = "update ProjectPriceEntity price set price.hdelete=1 where price.projectId="+id;
        projectPriceDao.updateByHql(hql);
        //删除项目下的课程
        hql = "update CourseEntity course set course.hdelete=1 where course.projectId="+id;
        courseDao.updateByHql(hql);
    }
}
