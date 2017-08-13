package com.sie.service.impl;

import com.sie.framework.dao.SchoolDao;
import com.sie.framework.entity.SchoolEntity;
import com.sie.service.SchoolService;
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
}
