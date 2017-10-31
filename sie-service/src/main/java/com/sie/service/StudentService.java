package com.sie.service;

import com.sie.framework.entity.StudentEntity;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.StudentExcelBean;
import com.sie.util.model.OAuthInfo;

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

    /**
     * 修改密码
     * @param userName
     * @param password
     * @return
     */
    ResultBean updatePassword(String userName, String password, String newPassword);

    ResultBean updateResetPassword(String userName);

    StudentEntity loginByOpenid(String openId);

    ResultBean updateApplicationStep(Integer studentId,Integer applicatioStep);
}
