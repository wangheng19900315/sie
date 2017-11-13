package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.StudentDao;
import com.sie.framework.entity.StudentEntity;
import com.sie.framework.help.ApplicationHelp;
import com.sie.service.StudentService;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.StudentExcelBean;
import com.sie.util.*;
import com.sie.util.model.OAuthInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<StudentEntity,Integer> implements StudentService {
    private StudentDao studentDao;

    private static final String defaultPassword = "123456";

    @Autowired
    public StudentServiceImpl(StudentDao studentDao){
        super(studentDao);
        this.studentDao = studentDao;
    }

    /**
     *
     * @param studentEntity
     * @param flag 1代表从后台信息进行修改  2代表前台修改申请表 3代表前台修改寄送地址
     * @return
     */
    public Integer saveOrUpdate(StudentEntity studentEntity,int flag){

        if(NumberUtil.isSignless(studentEntity.getId())){
            StudentEntity oldStudentEntity = this.studentDao.getEntity(studentEntity.getId());

            if(flag == 2 || flag == 1){
                //修改学生的申请表
                if(StringUtil.isNotBlank(studentEntity.getImage())){
                    oldStudentEntity.setImage(studentEntity.getImage());
                }
                oldStudentEntity.setSex(studentEntity.getSex());
                oldStudentEntity.setBirthday(studentEntity.getBirthday());
                oldStudentEntity.setChineseName(studentEntity.getChineseName());
                oldStudentEntity.setLastName(studentEntity.getLastName());
                oldStudentEntity.setFirstName(studentEntity.getFirstName());
                oldStudentEntity.setNationality(studentEntity.getNationality());
                oldStudentEntity.setIdNumber(studentEntity.getIdNumber());
                oldStudentEntity.setPassportNumber(studentEntity.getPassportNumber());
                oldStudentEntity.setTelephone(studentEntity.getTelephone());
                oldStudentEntity.setEmail(studentEntity.getEmail());
                oldStudentEntity.setWeiXin(studentEntity.getWeiXin());

                oldStudentEntity.setSchoolName(studentEntity.getSchoolName());
                oldStudentEntity.setProfession(studentEntity.getProfession());
                oldStudentEntity.setGpa(studentEntity.getGpa());
                oldStudentEntity.setGraduationYear(studentEntity.getGraduationYear());
                oldStudentEntity.setUnderstandWay(studentEntity.getUnderstandWay());
            }
//            oldStudentEntity.setUserName(studentEntity.getUserName());
//            oldStudentEntity.setUserID(studentEntity.getUserID());

            if(flag == 3 || flag == 1){
                //修改学生寄送信息
                oldStudentEntity.setSendPerson(studentEntity.getSendPerson());
                oldStudentEntity.setSendTel(studentEntity.getSendTel());
                oldStudentEntity.setSendStreet(studentEntity.getSendStreet());
                oldStudentEntity.setSendProvince(studentEntity.getSendProvince());
                oldStudentEntity.setSendCountry(studentEntity.getSendCountry());
                oldStudentEntity.setSendPostCode(studentEntity.getSendPostCode());
            }

            if(flag == 1){
                oldStudentEntity.setApplicationStep(studentEntity.getApplicationStep());
            }
            this.studentDao.updateEntity(oldStudentEntity);
            if(flag != 1){
                //学生进行修改需要更新步骤
                ResultBean resultBean = updateApplicationStep(studentEntity.getId(),studentEntity.getApplicationStep());
                if(!resultBean.isSuccess()){
                    throw new RuntimeException(resultBean.getMessage());
                }
            }
        }else{
            studentEntity.setUserID(DateUtil.format(new Date(), "yyyyMMddHHmmss")+ NumberUtil.randomInt(1000, 9999));
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
    public ResultBean updateEntity(StudentEntity studentEntity,int flag) {
        ResultBean resultBean = new ResultBean();
        StudentEntity oldEntity = this.studentDao.getEntity(studentEntity.getId());
        if(oldEntity == null){
            resultBean.setMessage("查找不到学生信息，请检查参数");
            return resultBean;
        }

        this.saveOrUpdate(studentEntity,flag);
        resultBean.setMessage("修改成功");
        resultBean.setSuccess(true);
        return resultBean;
    }

    @Override
    public boolean importBean(List<StudentExcelBean> beanList) {
        try {
            for(int i=0; i<beanList.size(); i++){
                StudentEntity  entity = new StudentEntity();
                BeanUtils.copyProperties(entity, beanList.get(i));
                //设置学生出生日期
                if(StringUtil.isNotBlank(beanList.get(i).getBirthdayFormate())){
                    entity.setBirthday(new java.sql.Date(DateUtil.parse(beanList.get(i).getBirthdayFormate(),"yyyy-MM-dd").getTime()));
                }
                //设置默认的登录密码为123456
                entity.setPassword(Md5Util.getMD5(defaultPassword, ApplicationHelp.MD5_SHA1));
                //设置用户名为邮箱
                entity.setUserName(beanList.get(i).getEmail());
                //设置默认的步骤为第4步
                entity.setApplicationStep(4);
                this.saveOrUpdate(entity,1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String repeatEntity(StudentExcelBean bean) {
        return repeat(bean.getIdNumber(),bean.getPassportNumber(),bean.getEmail());
    }

    @Override
    public String repeatEntity(StudentEntity entity) {
        return repeat(entity.getIdNumber(),entity.getPassportNumber(),entity.getEmail());
    }

    private String repeat(String idNumber,String passportNumber,String email){
        String result = null;
        String hql;
        if(StringUtil.isNotBlank(idNumber)){
            hql = "from StudentEntity where idNumber='"+ idNumber + "' and hdelete=0";
        }else if(StringUtil.isNotBlank(passportNumber)){
            hql = "from StudentEntity where passportNumber='"+ passportNumber + "' and hdelete=0";
        }else{
            result = "学生身份证号和者护照号都为空";
            return result;
        }
        if(!StringUtil.isNotBlank(email)){
            result = "学生邮箱为空";
            return result;
        }
        //判断学生信息是否重复
        List<StudentEntity> studentEntities = this.studentDao.getList(hql);
        if(studentEntities.size() > 0){
            result = idNumber +" "+passportNumber+ "学生信息重复";
            return result;
        }
        //判断用户邮箱是否重复
        hql = "from StudentEntity where email='" + email + "' and hdelete=0";
        studentEntities = this.studentDao.getList(hql);
        if(studentEntities.size() > 0){
            result = email + "已经被注册";
            return result;
        }
        return result;
    }

    @Override
    public ResultBean updatePassword(String userName, String password, String newPassword) {
        ResultBean resultBean = new ResultBean();
        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("userName", "=", userName));
        hqlOperateVos.add(new HqlOperateVo("password", "=", Md5Util.getMD5(password, ApplicationHelp.MD5_SHA1)));

        List<StudentEntity> studentEntityList = studentDao.getList(hqlOperateVos);
        if(studentEntityList == null || studentEntityList.size() == 0){
            resultBean.setMessage("原始密码不正确，请重新输入");
            return resultBean;
        }

        StudentEntity studentEntity = studentEntityList.get(0);
        studentEntity.setPassword(Md5Util.getMD5(newPassword, ApplicationHelp.MD5_SHA1));
        this.studentDao.updateEntity(studentEntity);
        resultBean.setMessage("修改成功");
        resultBean.setSuccess(true);
        return resultBean;
    }

    @Override
    public ResultBean updateResetPassword(String userName) {
        ResultBean resultBean = new ResultBean();
        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("userName", "=", userName));

        List<StudentEntity> studentEntityList = studentDao.getList(hqlOperateVos);
        if(studentEntityList == null || studentEntityList.size() == 0){
            resultBean.setMessage("用户不存在，请重新输入");
            return resultBean;
        }

        StudentEntity studentEntity = studentEntityList.get(0);
        String newPassword = (int)((Math.random()*9+1)*100000)+"";
        studentEntity.setPassword(Md5Util.getMD5(newPassword, ApplicationHelp.MD5_SHA1));
        this.studentDao.updateEntity(studentEntity);
        try {
            SendMailUtil.sendEmail("smtp.163.com","wangheng19900315@163.com", "900315", "wangheng19900315@163.com","网站密码修改","您的新密码为"+newPassword, studentEntity.getEmail());
        } catch (Exception e) {
            resultBean.setMessage("发送邮件失败");
            e.printStackTrace();
            return resultBean;
        }
        resultBean.setMessage("修改成功,密码已发送邮箱");
        resultBean.setSuccess(true);
        return resultBean;
    }

    @Override
    public StudentEntity loginByOpenid(String openId) {

        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("openid", "=", openId));

        List<StudentEntity> studentEntityList = studentDao.getList(hqlOperateVos);
        if(studentEntityList != null && studentEntityList.size() > 0){
            return studentEntityList.get(0);
        }else{
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setOpenid(openId);
            this.studentDao.createEntity(studentEntity);
            return studentEntity;
        }
    }

    @Override
    public ResultBean updateApplicationStep(Integer studentId, Integer applicatioStep) {
        ResultBean resultBean = new ResultBean();
        StudentEntity studentEntity = studentDao.getEntity(studentId);
        Integer oldApplicationStep = studentEntity.getApplicationStep();
        if(oldApplicationStep == null){
            oldApplicationStep = 0;//默认为注册成功
        }
        if(applicatioStep > oldApplicationStep){
            if(applicatioStep == (oldApplicationStep + 1)){
                //下一步的动作
                studentEntity.setApplicationStep(applicatioStep);
                this.studentDao.updateEntity(studentEntity);
            }else{
                resultBean.setMessage("学生申请步骤失败");
                return resultBean;
            }

        }
        resultBean.setMessage("设置步骤成功");
        resultBean.setSuccess(true);
        return resultBean;
    }

    @Override
    public ResultBean setDefaultPassword(Integer id) {
        ResultBean resultBean = new ResultBean();
        StudentEntity studentEntity = studentDao.getEntity(id);
        studentEntity.setPassword(Md5Util.getMD5(defaultPassword, ApplicationHelp.MD5_SHA1));
        studentDao.updateEntity(studentEntity);
        resultBean.setMessage("设置密码成功");
        resultBean.setSuccess(true);
        return resultBean;
    }

}


