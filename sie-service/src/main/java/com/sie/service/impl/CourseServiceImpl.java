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
@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<CourseEntity,Integer> implements CourseService {

    private CourseDao courseDao;

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

    public Integer saveOrUpdate(CourseEntity courseEntity) {
        if(NumberUtil.isSignless(courseEntity.getId())){
            CourseEntity oldProjectEntity = this.courseDao.getEntity(courseEntity.getId());
            //TODO 设置值
            this.courseDao.updateEntity(oldProjectEntity);
            return oldProjectEntity.getId();
        }else{
            genarateEntity(courseEntity);
            this.courseDao.createEntity(courseEntity);
            return courseEntity.getId();
        }
    }

    //根据选择的所属系统设置entity的值
    private void genarateEntity(CourseEntity courseEntity){
        SystemType systemType = SystemType.valueOf(courseEntity.getSystem());
        switch (systemType){
            case SIE:
                //将TRU的信息设置为空
                courseEntity.setTruCode(null);
                courseEntity.setTruMaxStudent(null);
                courseEntity.setSieTotalNumber(0);
                break;
            case TRU:
                //将SIE的信息设置为空
                courseEntity.setSieCode(null);
                courseEntity.setSieMaxStudent(null);
                courseEntity.setTruTotalNumber(0);
                break;
            case SIEANDTRU:
                courseEntity.setSieTotalNumber(0);
                courseEntity.setTruTotalNumber(0);
                break;
            default:
                courseEntity = null;
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
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}