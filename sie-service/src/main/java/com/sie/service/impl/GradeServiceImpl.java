package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.*;
import com.sie.framework.entity.*;
import com.sie.framework.type.OrderStatus;
import com.sie.framework.type.SystemType;
import com.sie.service.GradeService;
import com.sie.service.bean.GradeBean;
import com.sie.service.bean.PageInfo;
import com.sie.util.NumberUtil;
import com.sie.util.PageUtil;
import com.sie.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("gradeService")
public class GradeServiceImpl extends BaseServiceImpl<GradeEntity,Integer> implements GradeService {
    private GradeDao gradeDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    public GradeServiceImpl(GradeDao gradeDao){
        super(gradeDao);
        this.gradeDao = gradeDao;
    }


    @Override
    public PageInfo<GradeBean> getGradeList(Integer page, Integer rows, String  studentName) {

        if (!NumberUtil.isSignless(rows)) {
            rows = Integer.MAX_VALUE;
        }

        if (!NumberUtil.isSignless(page)) {
            page = 0;
        }
        Integer records = gradeDao.getCount(studentName);
        PageInfo<GradeBean> pageBean = new PageInfo<>(records, PageUtil.getPageTotal(records, rows));
        pageBean.setPage(PageUtil.getPageNow(page, pageBean.getTotal()));
        Integer firstResult = PageUtil.getFirstResult(pageBean.getPage(), rows);
        Integer maxResults = PageUtil.getMaxResults(rows);
        List<GradeEntity> entityList =  gradeDao.getList(firstResult, maxResults, studentName);

        List<GradeBean> orderBeanList = new ArrayList<>();
        if(entityList.size() > 0){
            for(GradeEntity detailEntity:entityList){
                GradeBean detailBean = new GradeBean();
                setDetailBeanValues(detailEntity, detailBean);
                orderBeanList.add(detailBean);
            }
            pageBean.setRows(orderBeanList);
        }

        pageBean.setPage(page);
        return pageBean;




    }

    public void setDetailBeanValues(GradeEntity gradeEntity, GradeBean bean){
        try{
            BeanUtils.copyProperties(bean, gradeEntity);
            if(gradeEntity.getCourseEntity() != null){
                bean.setCourseId(gradeEntity.getCourseEntity().getId());
                bean.setCourseName(gradeEntity.getCourseEntity().getEnglishName());
            }
            if(gradeEntity.getProjectEntity() != null){
                bean.setProjectId(gradeEntity.getProjectEntity().getId());
                if(gradeEntity.getSystemType() == SystemType.SIE.value()){
                    bean.setProjectName(gradeEntity.getProjectEntity().getSieName());
                }else{
                    bean.setProjectName(gradeEntity.getProjectEntity().getTruName());
                }

            }
            if(gradeEntity.getStudentEntity() != null){
                bean.setStudentId(gradeEntity.getStudentEntity().getId());
                bean.setStudentName(gradeEntity.getStudentEntity().getUserName());
            }

            if(NumberUtil.isSignless(gradeEntity.getSystemType())){
                SystemType type = SystemType.valueOf(gradeEntity.getSystemType());
                if(type != null){
                    bean.setSystemTypename(type.getName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public GradeBean getBean(Integer id) {
        GradeBean gradeBean = null;
        GradeEntity gradeEntity = this.gradeDao.getEntity(id);
        if(gradeEntity != null){
            gradeBean = new GradeBean();
            setDetailBeanValues(gradeEntity, gradeBean);

        }
        return gradeBean;
    }

    @Override
    public Integer updateGrade(GradeEntity gradeEntity) {
        GradeEntity oldEntity = this.gradeDao.getEntity(gradeEntity.getId());
        if(oldEntity != null){
            oldEntity.setGrade(gradeEntity.getGrade());
            this.gradeDao.updateEntity(oldEntity);
            return oldEntity.getId();
        }
        return null;
    }


    @Override
    public void updateStudentGradeList(Integer studentId) {
        StudentEntity studentEntity = this.studentDao.getEntity(studentId);
        if(studentEntity == null){
            return;
        }

        this.gradeDao.updateByHql("update GradeEntity g  set g.hdelete=1 where g.studentEntity.id="+studentId);

        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("studentEntity.id", "=", studentId+""));
        hqlOperateVos.add(new HqlOperateVo("status", "=", OrderStatus.COMPLETE.value()+""));
        List<OrderEntity> completeEntitys = this.orderDao.getList(hqlOperateVos);

        if(completeEntitys == null || completeEntitys.size() == 0){
            return;
        }

        String completeIds = "";
        Map<String,Integer> systeTypeMap = new HashMap<>();
        for(OrderEntity orderEntity:completeEntitys){
            for(OrderDetailEntity detailEntity:orderEntity.getOrderDetailEntityList()){
                if(detailEntity.getCourseIds() != null && detailEntity.getCourseIds().length() > 0){
                    completeIds += detailEntity.getCourseIds()+",";
                    String[] strs = detailEntity.getCourseIds().split(",");
                    for(String str:strs){
                        systeTypeMap.put(str, orderEntity.getSystemType());
                    }
                }
            }
        }

        Map<String, String> refunMap = new HashMap<>();
        hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("studentEntity.id", "=", studentId+""));
        hqlOperateVos.add(new HqlOperateVo("status", "=", OrderStatus.REFUND.value()+""));
        List<OrderEntity> refundEntitys = this.orderDao.getList(hqlOperateVos);
        for(OrderEntity orderEntity:refundEntitys){
            for(OrderDetailEntity detailEntity:orderEntity.getOrderDetailEntityList()){
                if(detailEntity.getCourseIds() != null && detailEntity.getCourseIds().length() > 0){
                    String[] strs = detailEntity.getCourseIds().split(",");
                    for(String str:strs){
                        refunMap.put(str, str);
                    }
                }
            }
        }


        if(StringUtil.isBlank(completeIds)){
            return;
        }


        String[] completeArray = completeIds.split(",");
        for(String completeId:completeArray){
            if(StringUtil.isNotBlank(completeId) && refunMap.get(completeId) == null){
                GradeEntity gradeEntity = new GradeEntity();
                CourseEntity courseEntity = this.courseDao.getEntity(Integer.parseInt(completeId));
                gradeEntity.setCourseEntity(courseEntity);
                gradeEntity.setGrade("0");
                gradeEntity.setStudentEntity(studentEntity);
                gradeEntity.setProjectEntity(this.projectDao.getEntity(courseEntity.getProjectId()));
                gradeEntity.setSystemType(systeTypeMap.get(completeId));
                this.gradeDao.createEntity(gradeEntity);
            }
        }








    }
}
