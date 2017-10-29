package com.sie.service.impl;

import com.google.common.collect.Maps;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.CourseDao;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.ProjectPriceDao;
import com.sie.framework.entity.CourseEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.ProjectPriceEntity;
import com.sie.framework.type.OrderType;
import com.sie.framework.type.SystemType;
import com.sie.service.CourseService;
import com.sie.service.bean.CourseBean;
import com.sie.service.bean.ProjectPriceBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.util.DateUtil;
import com.sie.util.NumberUtil;
import com.sie.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<CourseEntity,Integer> implements CourseService {

    private CourseDao courseDao;

    private static final String formString = "HH:mm:ss";
    private static final SimpleDateFormat format = new SimpleDateFormat(formString);

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    CourseServiceImpl(CourseDao courseDao) {
        super(courseDao);
        this.courseDao = courseDao;
    }


    @Override
    public PageInfo<CourseBean> getCourseList(Integer page, Integer rows,  List<HqlOperateVo> hqlOperateVos){
        PageInfo<CourseEntity> pageInfo = this.getList(page,rows, hqlOperateVos);
        PageInfo<CourseBean> result = new PageInfo<CourseBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<CourseBean> courseBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(CourseEntity courseEntity:pageInfo.getRows()){

                CourseBean bean = new CourseBean();
                setBeanValues(courseEntity, bean);
                courseBeanList.add(bean);
            }
            result.setRows(courseBeanList);
        }

        return result;
    }

    @Override
    public Integer saveOrUpdate(CourseBean courseBean) {
        genarateEntity(courseBean);

        if(courseBean == null){
            return null;
        }

        Date date;
        //将bean转化为entity
        CourseEntity courseEntity = new CourseEntity();
        try {
            BeanUtils.copyProperties(courseEntity,courseBean);
            //开始时间和结束时间用英文的:分开
            courseEntity.setStartTime(courseBean.getStartTime().replace("：", ":"));
            courseEntity.setEndTime(courseBean.getEndTime().replace("：", ":"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if(NumberUtil.isSignless(courseEntity.getId())){
            CourseEntity oldCourseEntity = this.courseDao.getEntity(courseBean.getId());
            //设置值
            oldCourseEntity.setSystem(courseEntity.getSystem());
            oldCourseEntity.setProjectId(courseEntity.getProjectId());
            oldCourseEntity.setChineseName(courseEntity.getChineseName());
            oldCourseEntity.setEnglishName(courseEntity.getEnglishName());
            oldCourseEntity.setStartTime(courseEntity.getStartTime());
            oldCourseEntity.setEndTime(courseEntity.getEndTime());
            oldCourseEntity.setMaxStudent(courseEntity.getMaxStudent());
            oldCourseEntity.setSieCode(courseEntity.getSieCode());
            oldCourseEntity.setTruCode(courseEntity.getTruCode());
            oldCourseEntity.setProfessorName(courseEntity.getProfessorName());
            oldCourseEntity.setSchool(courseEntity.getSchool());
            oldCourseEntity.setClassroom(courseEntity.getClassroom());
            this.courseDao.updateEntity(oldCourseEntity);
            return oldCourseEntity.getId();
        }else{
            courseEntity.setSieTotalNumber(0);
            courseEntity.setTruTotalNumber(0);
            this.courseDao.createEntity(courseEntity);
            //设置课程的ID
            courseEntity.setCourseID(String.format("%04d", courseEntity.getId()));
            courseDao.updateEntity(courseEntity);
            return courseEntity.getId();
        }
    }

    //根据选择的所属系统设置entity的值
    private void genarateEntity(CourseBean courseBean){
        SystemType systemType = SystemType.valueOf(courseBean.getSystem());
        switch (systemType){
            case SIE:
                //将TRU的信息设置为空
                courseBean.setTruCode(null);
                courseBean.setSieTotalNumber(0);
                break;
            case TRU:
                //将SIE的信息设置为空
                courseBean.setSieCode(null);
                courseBean.setTruTotalNumber(0);
                break;
            case SIEANDTRU:
                courseBean.setSieTotalNumber(0);
                courseBean.setTruTotalNumber(0);
                break;
            default:
                courseBean = null;
        }
    }

    private void setBeanValues(CourseEntity courseEntity, CourseBean bean){

        try{
            BeanUtils.copyProperties(bean, courseEntity);
            if(NumberUtil.isSignless(bean.getSystem())){
                SystemType systemType = SystemType.valueOf(bean.getSystem());
                if(systemType != null){
                    bean.setSystemName(systemType.getName());
                }
            }
            //设置projectname
            ProjectEntity projectEntity = projectDao.getEntity(courseEntity.getProjectId());
            bean.setProjectName(projectEntity.getSieName());

            //设置时间格式的字符串
//            if(bean.getStartTime() != null){
//                bean.setStartTimeFormat(format.format(bean.getStartTime()));
//            }
//            if(bean.getEndTime() != null){
//                bean.setEndTimeFormat(format.format(bean.getEndTime()));
//            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public List<CourseEntity> getCourses(Integer projectId, Integer systemType) {
        Map<Integer,String> courses = new HashMap<>();
        //得到属于当前系统和同事属于两个系统的
        String hql = "from CourseEntity where (system="+systemType+" or system="+SystemType.SIEANDTRU.value()+") and projectId="+projectId;
        List<CourseEntity> courseEntities = this.courseDao.getList(hql);
//        if(courseEntities.size()  >0){
//            for(CourseEntity courseEntity:courseEntities){
//                String code = "";
//                SystemType system = SystemType.valueOf(systemType);
//                switch (system){
//                    case SIE:
//                        code = courseEntity.getSieCode();
//                        break;
//                    case TRU:
//                        code = courseEntity.getTruCode();
//                        break;
//                }
//                  courses.put(courseEntity.getId(),code + "(" + courseEntity.getChineseName() + "," +courseEntity.getEnglishName()+ ")");
////                sb.append("<lable><input type='checkbox' name='courseIds' value='"+courseEntity.getId()+"'> "+courseEntity.getEnglishName() + "</lable>");
//            }
//        }
//        return courses;
        return courseEntities;
    }

    @Override
    public CourseBean getBean(Integer id) {
        CourseEntity entity = courseDao.getEntity(id);
        CourseBean bean = new CourseBean();
        try {
            setBeanValues(entity,bean);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bean;
    }

    /**
     * 更新课程报名人数
     * @param courseIds
     * @param systemType
     * @param orderType
     * @param flag 1表示报名人数加1，-1 表示报名人数减1
     */
    public void updateCourseCount(String courseIds, Integer systemType,  Integer orderType,Integer flag){
        if(StringUtil.isBlank(courseIds)){
            return;
        }
        String[] strs = courseIds.split(",");
        for(String str:strs){
            CourseEntity courseEntity = this.courseDao.getEntity(Integer.parseInt(str));
            if(courseEntity == null){
                throw new RuntimeException("id 为 "+str+"课程为空，请检查参数");
            }

            if(systemType == SystemType.SIE.value()){
                courseEntity.setSieTotalNumber(courseEntity.getSieTotalNumber()+flag);
            }else{
                courseEntity.setTruTotalNumber(courseEntity.getTruTotalNumber()+flag);
            }
            if(orderType == OrderType.USER.value() && flag > 0){
                if((courseEntity.getSieTotalNumber()+courseEntity.getTruTotalNumber()) > courseEntity.getMaxStudent()){
                    throw new RuntimeException("课程["+courseEntity.getChineseName()+"]人数最高能报"+courseEntity.getMaxStudent()+"");
                }
            }

            this.courseDao.updateEntity(courseEntity);

        }
    }
}