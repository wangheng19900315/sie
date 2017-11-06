package com.sie.service.impl;

import com.sie.framework.dao.SchoolDao;
import com.sie.framework.entity.SchoolEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.service.SchoolService;
import com.sie.service.bean.SchoolCategoryBean;
import com.sie.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("schoolService")
public class SchoolServiceImpl extends BaseServiceImpl<SchoolEntity,Integer> implements SchoolService {
    private SchoolDao schoolDao;

    @Autowired
    public SchoolServiceImpl(SchoolDao schoolDao){
        super(schoolDao);
        this.schoolDao = schoolDao;
    }

    public Integer saveOrUpdate(SchoolEntity schoolEntity){
        if(NumberUtil.isSignless(schoolEntity.getId())){
            SchoolEntity oldSchoolEntity = this.schoolDao.getEntity(schoolEntity.getId());
            oldSchoolEntity.setName(schoolEntity.getName());
            oldSchoolEntity.setProvince(schoolEntity.getProvince());
            oldSchoolEntity.setNationality(schoolEntity.getNationality());
            this.schoolDao.updateEntity(oldSchoolEntity);
        }else{
            this.schoolDao.createEntity(schoolEntity);
        }

        return schoolEntity.getId();
    }

    @Override
    public List<String> getAllSchoolName() {
        List<String> schools = new ArrayList<>();
        List<SchoolEntity> schoolEntities = schoolDao.getList();
        for(SchoolEntity entity : schoolEntities){
            schools.add(entity.getName());
        }
        return schools;
    }

    @Override
    public List<SchoolCategoryBean> getCategory() {
        List<SchoolCategoryBean> categoryBeanList = new ArrayList<>();
        List<Object[]> objects = this.schoolDao.getByHql("select nationality,province from SchoolEntity where hdelete=0  group by nationality,province");
        for(Object[] obj:objects){
            SchoolCategoryBean bean = new SchoolCategoryBean();
            bean.setNational(obj[0].toString());
            bean.setProvince(obj[1].toString());
            categoryBeanList.add(bean);
        }
        return categoryBeanList;
    }
}
