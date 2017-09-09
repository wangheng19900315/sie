package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.*;
import com.sie.framework.entity.*;
import com.sie.framework.type.OrderStatus;
import com.sie.framework.type.SystemType;
import com.sie.service.CourseService;
import com.sie.service.GradeSendService;
import com.sie.service.bean.CourseBean;
import com.sie.service.bean.GradeSendBean;
import com.sie.service.bean.PageInfo;
import com.sie.util.DateUtil;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("gradeSendService")
public class GradeSendServiceImpl extends BaseServiceImpl<GradeSendEntity,Integer> implements GradeSendService {

    private GradeSendDao gradeSendDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    GradeSendServiceImpl(GradeSendDao gradeSendDao) {
        super(gradeSendDao);
        this.gradeSendDao = gradeSendDao;
    }

    @Override
    public Integer saveOrUpdate(GradeSendEntity gradeSendEntity) {

        if(NumberUtil.isSignless(gradeSendEntity.getId())){
            GradeSendEntity oldGradeSendEntity = this.gradeSendDao.getEntity(gradeSendEntity.getId());
            //设置值
            oldGradeSendEntity.setExpressCompany(gradeSendEntity.getExpressCompany());
            oldGradeSendEntity.setTrackingNumber(gradeSendEntity.getTrackingNumber());
            if(oldGradeSendEntity.getDefaultSend() == 0){
                oldGradeSendEntity.setStudentId(gradeSendEntity.getStudentId());
                oldGradeSendEntity.setSendPerson(gradeSendEntity.getSendPerson());
                oldGradeSendEntity.setSendPostCode(gradeSendEntity.getSendPostCode());
                oldGradeSendEntity.setSendProvince(gradeSendEntity.getSendProvince());
                oldGradeSendEntity.setSendTel(gradeSendEntity.getSendTel());
                oldGradeSendEntity.setSendStreet(gradeSendEntity.getSendStreet());
                oldGradeSendEntity.setSendCountry(gradeSendEntity.getSendCountry());
            }

            this.gradeSendDao.updateEntity(oldGradeSendEntity);
            return oldGradeSendEntity.getId();
        }else{
            this.gradeSendDao.createEntity(gradeSendEntity);
        }
        return gradeSendEntity.getId();
    }

    @Override
    public PageInfo<GradeSendBean> getGradeSendList(Integer page, Integer rows,  List<HqlOperateVo> hqlOperateVos){
        PageInfo<GradeSendEntity> pageInfo = this.getList(page,rows, hqlOperateVos);
        PageInfo<GradeSendBean> result = new PageInfo<GradeSendBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<GradeSendBean> dormitoryBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(GradeSendEntity gradeSendEntity:pageInfo.getRows()){

                GradeSendBean bean = new GradeSendBean();
                setBeanValues(gradeSendEntity, bean);
                dormitoryBeanList.add(bean);
            }
            result.setRows(dormitoryBeanList);
        }

        return result;
    }

    @Override
    public List<GradeSendBean> getGradeSendList(List<HqlOperateVo> hqlOperateVos) {
        List<GradeSendEntity> gradeSendEntities = this.getList(hqlOperateVos);
        List<GradeSendBean> gradeSendBeanList = new ArrayList<>();
        for(GradeSendEntity gradeSendEntity:gradeSendEntities){

            GradeSendBean bean = new GradeSendBean();
            setBeanValues(gradeSendEntity, bean);
            gradeSendBeanList.add(bean);
        }
        return gradeSendBeanList;
    }

    private void setBeanValues(GradeSendEntity entity, GradeSendBean bean){

        try{
            BeanUtils.copyProperties(bean, entity);
            //设置studentname
            StudentEntity studentEntity = studentDao.getEntity(entity.getStudentId());
            bean.setStudentName(studentEntity.getChineseName());
            bean.setUserID(studentEntity.getUserID());

            if(entity.getDefaultSend() == 1){
                //默认订单需要设置寄送地址
                bean.setSendPerson(studentEntity.getSendPerson());
                bean.setSendPostCode(studentEntity.getSendPostCode());
                bean.setSendProvince(studentEntity.getSendProvince());
                bean.setSendTel(studentEntity.getSendTel());
                bean.setSendStreet(studentEntity.getSendStreet());
                bean.setSendCountry(studentEntity.getSendCountry());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public String importBean(GradeSendBean bean) {
        String result  = null;
        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("userID","=",bean.getUserID()));
        List<StudentEntity> studentEntities = this.studentDao.getList(hqlOperateVos);
        if(studentEntities == null || studentEntities.size() == 0){
            result = "学生信息不存在";
            return result;
        }
        hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("studentId","=",studentEntities.get(0).getId().toString()));
        hqlOperateVos.add(new HqlOperateVo("defaultSend","=","1"));
        List<GradeSendEntity>  gradeSendEntities = this.gradeSendDao.getList(hqlOperateVos);
        if(gradeSendEntities == null || gradeSendEntities.size() == 0){
            result = "成绩单寄送不存在";
            return result;
        }
        GradeSendEntity gradeSendEntity = gradeSendEntities.get(0);
        gradeSendEntity.setTrackingNumber(bean.getTrackingNumber());
        gradeSendEntity.setExpressCompany(bean.getExpressCompany());
        this.saveOrUpdate(gradeSendEntity);
        return result;
    }

    @Override
    public void updateStudentGradeSend(Integer studentId) {
        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("studentEntity.id", "=", studentId+""));
        hqlOperateVos.add(new HqlOperateVo("status", "=", OrderStatus.COMPLETE.value()+""));
        List<OrderEntity> completeOrders = this.orderDao.getList(hqlOperateVos);


        hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("studentId", "=", studentId+""));
        hqlOperateVos.add(new HqlOperateVo("defaultSend", "=", "1"));
        List<GradeSendEntity> gradeSendEntities = gradeSendDao.getList(hqlOperateVos);

        if(completeOrders.size() > 0){
            //学生存在已经完成的订单判断是否需要新增成绩单寄送
            if(gradeSendEntities.size() == 0){
                //如果不存在成绩单寄送进行添加
                GradeSendEntity gradeSendEntity = new GradeSendEntity();
                gradeSendEntity.setStudentId(studentId);
                gradeSendEntity.setDefaultSend(1);//设置为默认订单
                saveOrUpdate(gradeSendEntity);
            }
        }else{
            if(gradeSendEntities.size() > 0){
                //如果成绩单寄送存在进行删除
                gradeSendDao.updateByHql("update GradeSendEntity g  set g.hdelete=1 where g.studentId="+studentId);
            }
        }
    }

    @Override
    public GradeSendBean getGraseSendBean(Integer gradeSendId) {
        GradeSendEntity gradeSendEntity = gradeSendDao.getEntity(gradeSendId);
        GradeSendBean bean = new GradeSendBean();
        setBeanValues(gradeSendEntity, bean);
        return bean;
    }
}