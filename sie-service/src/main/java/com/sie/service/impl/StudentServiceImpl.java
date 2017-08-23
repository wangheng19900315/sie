package com.sie.service.impl;

import com.sie.framework.dao.StudentDao;
import com.sie.framework.entity.StudentEntity;
import com.sie.service.StudentService;
import com.sie.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            oldStudentEntity.setImage(studentEntity.getImage());
            oldStudentEntity.setBirthday(studentEntity.getBirthday());
            oldStudentEntity.setChineseName(studentEntity.getChineseName());
            oldStudentEntity.setUserName(studentEntity.getUserName());
            oldStudentEntity.setUserID(studentEntity.getUserID());
            oldStudentEntity.setEmail(studentEntity.getEmail());
            oldStudentEntity.setWeiXin(studentEntity.getWeiXin());
            oldStudentEntity.setLastName(studentEntity.getLastName());
            oldStudentEntity.setFirstName(studentEntity.getFirstName());
            oldStudentEntity.setNationality(studentEntity.getNationality());
            oldStudentEntity.setIdNumber(studentEntity.getIdNumber());
            oldStudentEntity.setPassportNumber(studentEntity.getPassportNumber());
            oldStudentEntity.setTelephone(studentEntity.getTelephone());
            oldStudentEntity.setUniversity(studentEntity.getUniversity());
            oldStudentEntity.setProfession(studentEntity.getProfession());
            oldStudentEntity.setGpa(studentEntity.getGpa());
            oldStudentEntity.setGraduationYear(studentEntity.getGraduationYear());
            this.studentDao.updateEntity(oldStudentEntity);
        }else{
            this.studentDao.createEntity(studentEntity);
        }

        return studentEntity.getId();
    }

    @Override
    public Map<Integer, String> getAllStudent() {
        List<StudentEntity> studentEntityList = studentDao.getList();
        Map<Integer,String> students = new HashMap<>();
        for(StudentEntity entity : studentEntityList){
            students.put(entity.getId(),entity.getChineseName());
        }
        return students;
    }
}
