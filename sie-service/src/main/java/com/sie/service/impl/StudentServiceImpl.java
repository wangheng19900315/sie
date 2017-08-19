package com.sie.service.impl;

import com.sie.framework.dao.StudentDao;
import com.sie.framework.entity.StudentEntity;
import com.sie.service.StudentService;
import com.sie.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<StudentEntity,Integer> implements StudentService {
    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao){
        super(studentDao);
        this.studentDao = studentDao;
    }

    public Integer saveOrUpdate(StudentEntity studentEntity){

        if(NumberUtil.isSignless(studentEntity.getId())){
            StudentEntity oldStudentEntity = this.studentDao.getEntity(studentEntity.getId());
            //TODO 设置信息
            this.studentDao.updateEntity(oldStudentEntity);
        }else{
            this.studentDao.createEntity(studentEntity);
        }

        return studentEntity.getId();
    }
}
