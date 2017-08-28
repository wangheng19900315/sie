package com.sie.service;

import com.sie.framework.entity.SchoolEntity;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface SchoolService extends BaseService<SchoolEntity, Integer> {

    List<String> getAllSchoolName();

}
