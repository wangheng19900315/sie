package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.StudentDao;
import com.sie.framework.entity.StudentEntity;
import com.sie.framework.help.ApplicationHelp;
import com.sie.service.StudentService;
import com.sie.service.bean.ResultBean;
import com.sie.util.DateUtil;
import com.sie.util.Md5Util;
import com.sie.util.NumberUtil;
import com.sie.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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
            if(StringUtil.isNotBlank(studentEntity.getImage())){
                oldStudentEntity.setImage(studentEntity.getImage());
            }
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
            oldStudentEntity.setSchoolName(studentEntity.getSchoolName());
            oldStudentEntity.setProfession(studentEntity.getProfession());
            oldStudentEntity.setGpa(studentEntity.getGpa());
            oldStudentEntity.setGraduationYear(studentEntity.getGraduationYear());
            oldStudentEntity.setSendPerson(studentEntity.getSendPerson());
            oldStudentEntity.setSendTel(studentEntity.getSendTel());
            oldStudentEntity.setSendProvince(studentEntity.getSendProvince());
            oldStudentEntity.setSendCountry(studentEntity.getSendCountry());
            oldStudentEntity.setSendPostCode(studentEntity.getSendPostCode());
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

    @Override
    public ResultBean register(String email, String weiXin, String password) {
        ResultBean resultBean = new ResultBean();

        String userName = null;
        if(StringUtil.isNotBlank(email)){
            userName = email;
        }else{
            userName = weiXin;
        }

        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("userName", "=", userName));
        List<StudentEntity> studentEntityList = studentDao.getList(hqlOperateVos);
        if(studentEntityList != null && studentEntityList.size() > 0){
            resultBean.setMessage("该用户名已经注册");
            return resultBean;
        }
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUserID(DateUtil.format(new Date(), "yyyyMMddHHmmss")+ NumberUtil.randomInt(1000, 9999));
        studentEntity.setEmail(email);
        studentEntity.setUserName(userName);
        studentEntity.setWeiXin(weiXin);
        studentEntity.setPassword(Md5Util.getMD5(password, ApplicationHelp.MD5_SHA1));
        this.studentDao.createEntity(studentEntity);
        resultBean.setMessage("注册成功");
        resultBean.setSuccess(true);
        return resultBean;
    }


    @Override
    public StudentEntity login(String userName, String password) {

        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("userName", "=", userName));
        hqlOperateVos.add(new HqlOperateVo("password", "=", Md5Util.getMD5(password, ApplicationHelp.MD5_SHA1)));

        List<StudentEntity> studentEntityList = studentDao.getList(hqlOperateVos);
        if(studentEntityList != null && studentEntityList.size() > 0){
             return studentEntityList.get(0);
        }

        return null;
    }

    @Override
    public ResultBean updateEntity(StudentEntity studentEntity) {
        ResultBean resultBean = new ResultBean();
        StudentEntity oldEntity = this.studentDao.getEntity(studentEntity.getId());
        if(oldEntity == null){
            resultBean.setMessage("查找不到学生信息，请检查参数");
            return resultBean;
        }

        this.saveOrUpdate(studentEntity);
        resultBean.setMessage("修改成功");
        resultBean.setSuccess(true);
        return resultBean;
    }
}
