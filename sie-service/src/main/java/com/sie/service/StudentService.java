package com.sie.service;

import com.sie.framework.entity.StudentEntity;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.StudentExcelBean;

import java.util.List;
import java.util.Map;


/**
 * Created by wangheng on 2017/8/9.
 */
public interface StudentService extends BaseService<StudentEntity, Integer> {

    Integer saveOrUpdate(StudentEntity studentEntity,int flag);

    Map<Integer,String> getAllStudent();

    ResultBean register(String email, String weiXin, String password);

    StudentEntity login(String userName, String password);

    ResultBean updateEntity(StudentEntity studentEntity,int flag);

    boolean importBean(List<StudentExcelBean> beanList);

    String repeatEntity(StudentExcelBean bean);

    String repeatEntity(StudentEntity entity);
}
