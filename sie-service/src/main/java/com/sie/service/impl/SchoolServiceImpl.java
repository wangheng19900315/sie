package com.sie.service.impl;

import com.sie.framework.dao.SchoolDao;
import com.sie.framework.entity.SchoolEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.service.SchoolService;
import com.sie.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            oldSchoolEntity.setAddress(schoolEntity.getAddress());
            this.schoolDao.updateEntity(schoolEntity);
        }else{
            this.schoolDao.createEntity(schoolEntity);
        }

        return schoolEntity.getId();
    }
}
