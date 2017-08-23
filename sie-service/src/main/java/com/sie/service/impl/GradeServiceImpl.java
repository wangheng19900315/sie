package com.sie.service.impl;

import com.sie.framework.dao.GradeDao;
import com.sie.framework.entity.GradeEntity;
import com.sie.framework.type.SystemType;
import com.sie.service.GradeService;
import com.sie.service.bean.GradeBean;
import com.sie.service.bean.PageInfo;
import com.sie.util.NumberUtil;
import com.sie.util.PageUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("gradeService")
public class GradeServiceImpl extends BaseServiceImpl<GradeEntity,Integer> implements GradeService {
    private GradeDao gradeDao;

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
}
