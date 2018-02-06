package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.CourseDao;
import com.sie.framework.dao.DormitoryDao;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.dao.ProjectPriceDao;
import com.sie.framework.entity.CourseEntity;
import com.sie.framework.entity.DormitoryEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.entity.ProjectPriceEntity;
import com.sie.framework.type.School;
import com.sie.framework.type.SystemType;
import com.sie.service.ProjectService;
import com.sie.service.bean.ProjectPriceBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ProjectBean;
import com.sie.service.excel.StudentCourseExport;
import com.sie.service.vo.CourseVo;
import com.sie.service.vo.CourseVoMap;
import com.sie.service.vo.DormitoryVo;
import com.sie.service.vo.ProjectVo;
import com.sie.util.DateUtil;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<ProjectEntity,Integer> implements ProjectService {

    private ProjectDao projectDao;

    private static final String formString = "yyyy-MM-dd";
    private static final SimpleDateFormat format = new SimpleDateFormat(formString);

//    @Autowired
//    private ProjectPriceDao projectPriceDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private DormitoryDao dormitoryDao;

    @Autowired
    ProjectServiceImpl(ProjectDao projectDao) {
        super(projectDao);
        this.projectDao = projectDao;
    }


    @Override
    public PageInfo<ProjectBean> getProjectList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVos){
        PageInfo<ProjectEntity> pageInfo = this.getList(page,rows, hqlOperateVos);
        PageInfo<ProjectBean> result = new PageInfo<ProjectBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<ProjectBean> projectBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(ProjectEntity projectEntity:pageInfo.getRows()){

                ProjectBean bean = new ProjectBean();
                setBeanValues(projectEntity, bean);
                projectBeanList.add(bean);
            }
            result.setRows(projectBeanList);
        }

        return result;
    }

    @Override
    public List<ProjectBean> getProjectList( List<HqlOperateVo> hqlOperateVos){
        List<ProjectEntity> projectEntities = this.getList(hqlOperateVos);

        List<ProjectBean> projectBeanList = new ArrayList<>();

        for(ProjectEntity projectEntity:projectEntities){

            ProjectBean bean = new ProjectBean();
            setBeanValues(projectEntity, bean);
            //TODO 得到项目下所有课程SIE和TRU项目总工报名人数进行相加
            String hql = "from CourseEntity where projectId='"+projectEntity.getId()+"' and hdelete=0";
            List<CourseEntity> courseEntities = courseDao.getList(hql);
            int sieNum = 0;
            int truNum = 0;
            for(CourseEntity courseEntity : courseEntities){
                if(courseEntity.getSieTotalNumber() != null){
                    sieNum = sieNum + courseEntity.getSieTotalNumber().intValue();
                }
                if(courseEntity.getTruTotalNumber() != null){
                    truNum = truNum + courseEntity.getTruTotalNumber().intValue();
                }
            }
            bean.setSieNumber(sieNum);
            bean.setTruNumber(truNum);
            projectBeanList.add(bean);
        }

        return projectBeanList;
    }

    @Override
    public Integer saveOrUpdate(ProjectBean projectBean) {
        genarateEntity(projectBean);
        if(projectBean == null){
            return null;
        }
//        if(!validatorBean(projectBean)){
//            return null;
//        }
        Date date;
        //将bean转化为entity
        ProjectEntity projectEntity = new ProjectEntity();
        try {
            BeanUtils.copyProperties(projectEntity,projectBean);
            //设置时间格式
            date = DateUtil.parse(projectBean.getStartTimeFormat(),formString);
            projectEntity.setStartTime(new Timestamp(date.getTime()));
            date = DateUtil.parse(projectBean.getEndTimeFormat(),formString);
            projectEntity.setEndTime(new Timestamp(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if(NumberUtil.isSignless(projectEntity.getId())){
            ProjectEntity oldProjectEntity = this.projectDao.getEntity(projectBean.getId());
            oldProjectEntity.setCode(projectEntity.getCode());
            oldProjectEntity.setSystem(projectEntity.getSystem());
            oldProjectEntity.setStartTime(projectEntity.getStartTime());
            oldProjectEntity.setEndTime(projectEntity.getEndTime());
            oldProjectEntity.setSieName(projectEntity.getSieName());
            oldProjectEntity.setSieMaxCourse(projectEntity.getSieMaxCourse());
            oldProjectEntity.setTruName(projectEntity.getTruName());
            oldProjectEntity.setTruMaxCourse(projectEntity.getTruMaxCourse());
            oldProjectEntity.setArea(projectEntity.getArea());
            oldProjectEntity.setEnabled(projectEntity.getEnabled());
            this.projectDao.updateEntity(oldProjectEntity);
//            return oldProjectEntity.getId();
        }else{
            this.projectDao.createEntity(projectEntity);
        }
        //TODO 保存价格
//        if(NumberUtil.isSignless(projectEntity.getId())){
//            saveOrUpdateProjectPrice(projectEntity.getId(),projectBean);
//        }
        return projectEntity.getId();
    }

    @Override
    public Map<Integer, String> getAllCourseProject(Integer system) {
        String hql = "from ProjectEntity";
        if(system != null){
            //得到属于本系统或者同事属于两个系统共有的
            hql = hql + " where system="+system+" or system="+SystemType.SIEANDTRU.value();
        }
        List<ProjectEntity> projectEntities = projectDao.getList(hql);
        Map<Integer,String> projects = new HashedMap();
        //TODO 应该选择可以添加课程的项目
        for(ProjectEntity entity : projectEntities){
            projects.put(entity.getId(),entity.getCode());
        }
        return projects;
    }

    @Override
    public ProjectBean getBean(Integer id) {
        ProjectEntity entity = projectDao.getEntity(id);
        ProjectBean bean = new ProjectBean();
        try {
            setBeanValues(entity,bean);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

//        ProjectPriceBean[] siePrice = new ProjectPriceBean[5];
//        ProjectPriceBean[] truPrice = new ProjectPriceBean[5];
        //设置price
//        String hql = "from ProjectPriceEntity where system=1 and projectId='" + id + "' and hdelete=0 order by courseNumber";
//        List<ProjectPriceEntity> siePrices = projectPriceDao.getList(hql);
//        for(int i = 0; i < siePrices.size(); i++){
//            ProjectPriceBean priceBean = new ProjectPriceBean();
//            entityToBean(siePrices.get(i),priceBean);
//            siePrice[i] = priceBean;
//        }
//
//        hql = "from ProjectPriceEntity where system=2 and projectId='" + id + "' and hdelete=0 order by courseNumber";
//        List<ProjectPriceEntity> truPrices = projectPriceDao.getList(hql);
//        for(int i = 0; i < truPrices.size(); i++){
//            ProjectPriceBean priceBean = new ProjectPriceBean();
//            entityToBean(truPrices.get(i),priceBean);
//            truPrice[i] = priceBean;
//        }
//        bean.setSiePrice(siePrice);
//        bean.setTruPrice(truPrice);
        return bean;
    }

    @Override
    public ProjectVo getProjectVoDetail(Integer id,SystemType systemType) {
        ProjectEntity projectEntity = projectDao.getEntity(id);
        ProjectVo vo = new ProjectVo();
        try {
            BeanUtils.copyProperties(vo,projectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(projectEntity.getStartTime() != null){
            vo.setStartTimeStr(DateUtil.format(projectEntity.getStartTime(), "yyyy/MM/dd"));
        }
        if(projectEntity.getEndTime() != null){
            vo.setEndTimeStr(DateUtil.format(projectEntity.getEndTime(), "yyyy/MM/dd"));
        }
        //根据系统设置项目的名称和最大课程数
        if(systemType == SystemType.SIE){
            //sie系统
            vo.setName(projectEntity.getSieName());
            vo.setMaxCourse(projectEntity.getSieMaxCourse());
        }else{
            //tru系统
            vo.setName(projectEntity.getTruName());
            vo.setMaxCourse(projectEntity.getTruMaxCourse());
        }
        //设置项目为单个项目
        vo.setIds(projectEntity.getId().toString());

        //设置项目的课程信息
        List<HqlOperateVo> list = new  ArrayList<HqlOperateVo>();
        list.add(new HqlOperateVo("system", "in", systemType.value()+","+ SystemType.SIEANDTRU.value()));
        list.add(new HqlOperateVo("projectEntity.id", "=", id.toString()));
        List<CourseEntity> courseEntities = courseDao.getList(list);
        Map<String,List<CourseVo>> courseVoMap = new HashMap<>();
        for(CourseEntity courseEntity : courseEntities){
            CourseVo courseVo = new CourseVo();
            try {
                BeanUtils.copyProperties(courseVo,courseEntity);
                if(courseEntity.getSchool() != null){
                    //设置校区名称
                    School school = School.valueOf(courseEntity.getSchool());
                    courseVo.setSchoolName(school.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //设置课程编码
            if(systemType == SystemType.SIE){
                courseVo.setChineseName(courseEntity.getSieChineseName());
                courseVo.setEnglishName(courseEntity.getSieEnglishName());
                courseVo.setCode(courseEntity.getSieCode());
            }else{
                courseVo.setChineseName(courseEntity.getTruChineseName());
                courseVo.setEnglishName(courseEntity.getTruEnglishName());
                courseVo.setCode(courseEntity.getTruCode());
            }
            //判断课程人数是否报满
            if((courseEntity.getSieTotalNumber() + courseEntity.getTruTotalNumber()) >= courseEntity.getMaxStudent()){
                courseVo.setReadonly(true);
            }

            //得到上课时间的key 8：30 -- 10：30
            // 设置开始时间和结束时间都为5位
            String startTime = courseVo.getStartTime();
            if(startTime.length() == 4){
                startTime = "0" + startTime;
            }
            String endTime = courseVo.getEndTime();
            if(endTime.length() == 4){
                endTime = "0" + endTime;
            }
            String key = startTime + " -- " + endTime;
            if(courseVoMap.get(key) == null){
                List<CourseVo> vos = new ArrayList<>();
                vos.add(courseVo);
                courseVoMap.put(key,vos);
            }else{
                courseVoMap.get(key).add(courseVo);
            }
        }

        //对上课时间进行排序
        List<String> keys = new ArrayList<>();
        for(String key : courseVoMap.keySet()){
            keys.add(key);
        }
        Collections.sort(keys);
        List<CourseVoMap> courseVoMaps = new ArrayList<>();
        for(String key : keys){
            CourseVoMap map = new CourseVoMap();
            map.setCourseVos(courseVoMap.get(key));
            CourseVo courseVo = courseVoMap.get(key).get(0);
            map.setKey(courseVo.getStartTime() + " -- " + courseVo.getEndTime());
            courseVoMaps.add(map);
        }

        vo.setCourseVoMaps(courseVoMaps);
        //设置项目的住宿信息
        list = new  ArrayList<HqlOperateVo>();
        list.add(new HqlOperateVo("projectId", "=", id.toString()));
        List<DormitoryEntity> dormitoryEntities = dormitoryDao.getList(list);
        List<DormitoryVo> dormitoryVos = new ArrayList<>();
        for(DormitoryEntity dormitoryEntity : dormitoryEntities){
            DormitoryVo dormitoryVo = new DormitoryVo();
            dormitoryVo.setId(dormitoryEntity.getId());
            dormitoryVo.setName(dormitoryEntity.getName());
            dormitoryVo.setPrice(dormitoryEntity.getPrice());
            if(dormitoryEntity.getTotalNumber() >= dormitoryEntity.getMaxNumber()){
                dormitoryVo.setReadonly(true);
            }
            dormitoryVos.add(dormitoryVo);
        }
        vo.setDormitoryVos(dormitoryVos);

        return vo;
    }

    @Override
    public List<StudentCourseExport> getStudentInCourse(Integer projectId) {
        String sql = "SELECT\n" +
                "\tt2.system_type,t3.course_ids,t1.chinese_name,t1.first_name,t1.last_name,t1.telephone,t1.email,t1.wei_xin,t2.`code`,t1.sex\n" +
                "FROM\n" +
                "\tt_student_info t1\n" +
                "JOIN t_order_info t2 ON t1.id = t2.student_id\n" +
                "JOIN t_order_detail_info t3 ON t2.id = t3.order_id\n" +
                "WHERE\n" +
                "\tt1.h_delete = 0\n" +
                "AND t2.h_delete = 0\n" +
                "AND t3.h_delete = 0\n" +
                "AND t2.`status` = '2'\n" +
                "AND t3.dormitory_id IS NULL\n" +
                "AND t3.project_id = '"+ projectId +"'";

        ProjectEntity projectEntity = projectDao.getEntity(projectId);
        List<Object[]> students = projectDao.getBySql(sql);
        Map<Integer,List<StudentCourseExport>> studentMap = new HashedMap();
        for(Object[] student : students){
            Integer systemType = (Integer) student[0];
            SystemType system = SystemType.valueOf(systemType);
            String courses = (String)student[1];
            String[] courseIds = courses.split(",");
            for(String courseId : courseIds){
                CourseEntity courseEntity = courseDao.getEntity(Integer.parseInt(courseId));
                StudentCourseExport studentCourseExport = new StudentCourseExport();
                studentCourseExport.setCourseID(courseEntity.getCourseID());
                if(system == SystemType.SIE){
                    studentCourseExport.setCourseCode(courseEntity.getSieCode());
                    studentCourseExport.setCourseEnglishName(courseEntity.getSieEnglishName());
                }else{
                    studentCourseExport.setCourseCode(courseEntity.getTruCode());
                    studentCourseExport.setCourseEnglishName(courseEntity.getTruEnglishName());
                }

                studentCourseExport.setProjectCode(projectEntity.getCode());
                studentCourseExport.setProfessor(courseEntity.getProfessorName());
                studentCourseExport.setCourseTime(courseEntity.getStartTime() + "--" + courseEntity.getEndTime());
                //学生总人数最后算

                //学生信息
                studentCourseExport.setChineseName((String)student[2]);
                studentCourseExport.setFirstName((String)student[3]);
                studentCourseExport.setLastName((String)student[4]);
                studentCourseExport.setTelephone((String)student[5]);
                studentCourseExport.setEmail((String)student[6]);
                studentCourseExport.setWeiXin((String)student[7]);
                studentCourseExport.setOrderCode((String)student[8]);
                studentCourseExport.setSex((String)student[9]);
                int otherCourseName = 1;
                //设置其他课程
                for(String otherCourse : courseIds){
                    if(!otherCourse.equals(courseId)){
                        CourseEntity otherCourseEntity = courseDao.getEntity(Integer.parseInt(otherCourse));
                        if(otherCourseName == 1){
                            if(system == SystemType.SIE){
                                studentCourseExport.setOtherCourse1(otherCourseEntity.getSieEnglishName());
                            }else{
                                studentCourseExport.setOtherCourse1(otherCourseEntity.getTruEnglishName());
                            }
                            otherCourseName ++ ;
                        }else if(otherCourseName == 2){
                            if(system == SystemType.SIE){
                                studentCourseExport.setOtherCourse1(otherCourseEntity.getSieEnglishName());
                            }else{
                                studentCourseExport.setOtherCourse1(otherCourseEntity.getTruEnglishName());
                            }
                            otherCourseName ++ ;
                        }else{
                            //目前不存在一个项目下报名4门课程
                        }
                    }
                }
                if(studentMap.get(courseEntity.getId()) == null){
                    List<StudentCourseExport> studentList = new ArrayList<>();
                    studentList.add(studentCourseExport);
                    studentMap.put(courseEntity.getId(),studentList);
                }else{
                    studentMap.get(courseEntity.getId()).add(studentCourseExport);
                }

            }

        }
        //设置课程数量
        for(Integer key : studentMap.keySet()){
            for(StudentCourseExport student : studentMap.get(key)){
                student.setCourseNumber(studentMap.get(key).size());
            }
        }

        List<StudentCourseExport> studentCourseExports = new ArrayList<>();
        for(Integer key : studentMap.keySet()){
            studentCourseExports.addAll(studentMap.get(key));
            //添加一行空格
            studentCourseExports.add(new StudentCourseExport());
        }
        //去掉最后一行
        studentCourseExports.remove(studentCourseExports.size() - 1);
        return studentCourseExports;
    }

    private void entityToBean(ProjectPriceEntity entity,ProjectPriceBean bean){
        try {
            BeanUtils.copyProperties(bean, entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer savePrice(ProjectPriceBean price) {
        ProjectPriceEntity projectPriceEntity = new ProjectPriceEntity();
        try {
            BeanUtils.copyProperties(projectPriceEntity,price);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        this.projectDao.createEntity(projectPriceEntity);
        return projectPriceEntity.getId();
    }

//    private boolean validatorBean(ProjectBean bean){
//        if((bean.getSieMaxCourse() != null) && (bean.getSieMaxCourse() > bean.getSiePrice().length)){
//            return false;
//        }
//        if((bean.getTruMaxCourse() != null) && (bean.getTruMaxCourse() > bean.getTruPrice().length)){
//            return false;
//        }
//        return true;
//    }


//    private void saveOrUpdateProjectPrice(Integer projectId, ProjectBean bean){
//        //删除project下边的price
//        String hql = "update ProjectPriceEntity price set price.hdelete=1 where price.projectId="+projectId;
//        projectPriceDao.updateByHql(hql);
//
//        ProjectPriceBean[] siePrice = bean.getSiePrice();
//        ProjectPriceBean[] truPrice = bean.getTruPrice();
//        //保存sie价格
//        if(bean.getSieMaxCourse() != null){
//            for(int i = 0; i < siePrice.length && i < bean.getSieMaxCourse(); i ++){
//                ProjectPriceBean priceBean = siePrice[i];
//                priceBean.setSystem(1);
//                priceBean.setProjectId(projectId);
//                savePrice(priceBean);
//            }
//        }
//
//        //保存tru价格
//        if(bean.getTruMaxCourse() != null){
//            for(int i = 0; i < truPrice.length && i < bean.getTruMaxCourse(); i ++){
//                ProjectPriceBean priceBean = truPrice[i];
//                priceBean.setSystem(2);
//                priceBean.setProjectId(projectId);
//                savePrice(priceBean);
//            }
//        }
//
//    }

    private void setBeanValues(ProjectEntity projectEntity, ProjectBean bean){

        try{
            BeanUtils.copyProperties(bean, projectEntity);
            if(NumberUtil.isSignless(bean.getSystem())){
                SystemType systemType = SystemType.valueOf(bean.getSystem());
                if(systemType != null){
                    bean.setSystemName(systemType.getName());
                }
            }
            //设置时间格式的字符串
            if(bean.getStartTime() != null){
                bean.setStartTimeFormat(format.format(bean.getStartTime()));
            }
            if(bean.getEndTime() != null){
                bean.setEndTimeFormat(format.format(bean.getEndTime()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //根据选择的所属系统设置entity的值
    private void genarateEntity(ProjectBean projectBean){
        SystemType systemType = SystemType.valueOf(projectBean.getSystem());
        switch (systemType){
            case SIE:
                //将TRU的信息设置为空
                projectBean.setTruMaxCourse(null);
                projectBean.setTruName(null);
                break;
            case TRU:
                //将SIE的信息设置为空
                projectBean.setSieMaxCourse(null);
                projectBean.setSieName(null);
                break;
            case SIEANDTRU:
                break;
            default:
                projectBean = null;
        }
    }

    @Override
    public void delete(Integer id) {
        //删除project
        projectDao.deleteEntity(projectDao.getEntity(id));
//        //删除项目下的价格
//        String hql = "update ProjectPriceEntity price set price.hdelete=1 where price.projectId="+id;
//        projectPriceDao.updateByHql(hql);
        //删除项目下的课程
        String hql = "update CourseEntity course set course.hdelete=1 where course.projectEntity.id="+id;
        courseDao.updateByHql(hql);
    }
}
