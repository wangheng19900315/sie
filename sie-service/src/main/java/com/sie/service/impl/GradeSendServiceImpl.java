package com.sie.service.impl;

import com.sie.framework.dao.CourseDao;
import com.sie.framework.dao.GradeSendDao;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.StudentDao;
import com.sie.framework.entity.CourseEntity;
import com.sie.framework.entity.GradeSendEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.StudentEntity;
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
    GradeSendServiceImpl(GradeSendDao gradeSendDao) {
        super(gradeSendDao);
        this.gradeSendDao = gradeSendDao;
    }

    @Override
    public Integer saveOrUpdate(GradeSendEntity gradeSendEntity) {

        if(NumberUtil.isSignless(gradeSendEntity.getId())){
            GradeSendEntity oldgradeSendEntity = this.gradeSendDao.getEntity(gradeSendEntity.getId());
            //设置值
            oldgradeSendEntity.setExpressCompany(gradeSendEntity.getExpressCompany());
            oldgradeSendEntity.setSendCountry(gradeSendEntity.getSendCountry());
            oldgradeSendEntity.setSendPerson(gradeSendEntity.getSendPerson());
            oldgradeSendEntity.setSendPostCode(gradeSendEntity.getSendPostCode());
            oldgradeSendEntity.setSendProvince(gradeSendEntity.getSendProvince());
            oldgradeSendEntity.setSendTel(gradeSendEntity.getSendTel());
            oldgradeSendEntity.setStudentId(gradeSendEntity.getStudentId());
            oldgradeSendEntity.setTrackingNumber(gradeSendEntity.getTrackingNumber());
            this.gradeSendDao.updateEntity(oldgradeSendEntity);
            return oldgradeSendEntity.getId();
        }else{
            this.gradeSendDao.createEntity(gradeSendEntity);
        }
        return gradeSendEntity.getId();
    }

    @Override
    public PageInfo<GradeSendBean> getGradeSendList(Integer page, Integer rows, Map<String, Object> parameter){
        PageInfo<GradeSendEntity> pageInfo = this.getList(page,rows, parameter);
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

    private void setBeanValues(GradeSendEntity entity, GradeSendBean bean){

        try{
            BeanUtils.copyProperties(bean, entity);
            //设置studentname
            StudentEntity studentEntity = studentDao.getEntity(entity.getStudentId());
            bean.setStudentName(studentEntity.getChineseName());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}