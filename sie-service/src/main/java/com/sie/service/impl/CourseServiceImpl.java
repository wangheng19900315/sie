package com.sie.service.impl;

import com.sie.framework.dao.CourseDao;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.ProjectPriceDao;
import com.sie.framework.entity.CourseEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.ProjectPriceEntity;
import com.sie.framework.type.SystemType;
import com.sie.service.CourseService;
import com.sie.service.bean.CourseBean;
import com.sie.service.bean.ProjectPriceBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.util.DateUtil;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<CourseEntity,Integer> implements CourseService {

    private CourseDao courseDao;

    private static final String formString = "HH:mm:ss";
    private static final SimpleDateFormat format = new SimpleDateFormat(formString);

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    CourseServiceImpl(CourseDao courseDao) {
        super(courseDao);
        this.courseDao = courseDao;
    }


    @Override
    public PageInfo<CourseBean> getCourseList(Integer page, Integer rows, Map<String, Object> parameter){
        PageInfo<CourseEntity> pageInfo = this.getList(page,rows, parameter);
        PageInfo<CourseBean> result = new PageInfo<CourseBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<CourseBean> courseBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(CourseEntity courseEntity:pageInfo.getRows()){

                CourseBean bean = new CourseBean();
                setBeanValues(courseEntity, bean);
                courseBeanList.add(bean);
            }
            result.setRows(courseBeanList);
        }

        return result;
    }

    @Override
    public Integer saveOrUpdate(CourseBean courseBean) {
        genarateEntity(courseBean);

        if(courseBean == null){
            return null;
        }

        Date date;
        //将bean转化为entity
        CourseEntity courseEntity = new CourseEntity();
        try {
            BeanUtils.copyProperties(courseEntity,courseBean);
            //设置时间格式
            date = DateUtil.parse(courseBean.getStartTimeFormat(),formString);
            courseEntity.setStartTime(new Timestamp(date.getTime()));
            date = DateUtil.parse(courseBean.getEndTimeFormat(),formString);
            courseEntity.setEndTime(new Timestamp(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if(NumberUtil.isSignless(courseEntity.getId())){
            CourseEntity oldProjectEntity = this.courseDao.getEntity(courseBean.getId());
            //设置值
            oldProjectEntity.setChineseName(courseEntity.getChineseName());
            oldProjectEntity.setEnglishName(courseEntity.getEnglishName());
            oldProjectEntity.setStartTime(courseEntity.getStartTime());
            oldProjectEntity.setEndTime(courseEntity.getEndTime());
            oldProjectEntity.setMaxStudent(courseEntity.getMaxStudent());
            oldProjectEntity.setSieCode(courseEntity.getSieCode());
            oldProjectEntity.setTruCode(courseEntity.getTruCode());
            this.courseDao.updateEntity(oldProjectEntity);
            return oldProjectEntity.getId();
        }else{

            this.courseDao.createEntity(courseEntity);
            return courseEntity.getId();
        }
    }

    //根据选择的所属系统设置entity的值
    private void genarateEntity(CourseBean courseBean){
        SystemType systemType = SystemType.valueOf(courseBean.getSystem());
        switch (systemType){
            case SIE:
                //将TRU的信息设置为空
                courseBean.setTruCode(null);
                courseBean.setSieTotalNumber(0);
                break;
            case TRU:
                //将SIE的信息设置为空
                courseBean.setSieCode(null);
                courseBean.setTruTotalNumber(0);
                break;
            case SIEANDTRU:
                courseBean.setSieTotalNumber(0);
                courseBean.setTruTotalNumber(0);
                break;
            default:
                courseBean = null;
        }
    }

    private void setBeanValues(CourseEntity courseEntity, CourseBean bean){

        try{
            BeanUtils.copyProperties(bean, courseEntity);
            if(NumberUtil.isSignless(bean.getSystem())){
                SystemType systemType = SystemType.valueOf(bean.getSystem());
                if(systemType != null){
                    bean.setSystemName(systemType.getName());
                }
            }
            //设置projectname
            ProjectEntity projectEntity = projectDao.getEntity(courseEntity.getProjectId());
            bean.setProjectName(projectEntity.getSieName());

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

    @Override
    public String getCourseCheckbox(Integer projectId, Integer systemType) {
        StringBuilder sb = new StringBuilder();
        String hql = "from CourseEntity where system="+systemType+" and projectId="+projectId;
        List<CourseEntity> courseEntities = this.courseDao.getList(hql);
        if(courseEntities.size()  >0){
            for(CourseEntity courseEntity:courseEntities){
                sb.append("<input type='checkbox' name='courseIds' value='"+courseEntity.getId()+"'> "+courseEntity.getEnglishName());
            }
        }
        return sb.toString();
    }

    @Override
    public CourseBean getBean(Integer id) {
        CourseEntity entity = courseDao.getEntity(id);
        CourseBean bean = new CourseBean();
        try {
            setBeanValues(entity,bean);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bean;
    }
}