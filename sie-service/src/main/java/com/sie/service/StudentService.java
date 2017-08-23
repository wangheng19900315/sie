package com.sie.service;

import com.sie.framework.entity.StudentEntity;

import java.util.Map;


/**
 * Created by wangheng on 2017/8/9.
 */
public interface StudentService extends BaseService<StudentEntity, Integer> {
    Map<Integer,String> getAllStudent();
}
