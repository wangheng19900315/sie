package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.DormitoryDao;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.entity.DormitoryEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.framework.type.OrderType;
import com.sie.framework.type.SystemType;
import com.sie.service.DormitoryService;
import com.sie.service.bean.DormitoryBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.excel.StudentDormitoryExport;
import com.sie.util.DateUtil;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("dormitoryService")
public class DormitoryServiceImpl extends BaseServiceImpl<DormitoryEntity,Integer> implements DormitoryService {
    private DormitoryDao dormitoryDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    public DormitoryServiceImpl(DormitoryDao dormitoryDao){
        super(dormitoryDao);
        this.dormitoryDao = dormitoryDao;
    }

    public Integer saveOrUpdate(DormitoryEntity dormitoryEntity){

        if(NumberUtil.isSignless(dormitoryEntity.getId())){
            DormitoryEntity oldDormitoryEntity = this.dormitoryDao.getEntity(dormitoryEntity.getId());
            oldDormitoryEntity.setName(dormitoryEntity.getName());
            oldDormitoryEntity.setPrice(dormitoryEntity.getPrice());
            oldDormitoryEntity.setCode(dormitoryEntity.getCode());
            oldDormitoryEntity.setMaxNumber(dormitoryEntity.getMaxNumber());
            oldDormitoryEntity.setAddress(dormitoryEntity.getAddress());
            oldDormitoryEntity.setProjectId(dormitoryEntity.getProjectId());
            this.dormitoryDao.updateEntity(oldDormitoryEntity);
        }else{
            dormitoryEntity.setWomanNumber(0);
            dormitoryEntity.setManNumber(0);
            dormitoryEntity.setTotalNumber(0);
            dormitoryEntity.setSieNumber(0);
            dormitoryEntity.setTruNumber(0);
            this.dormitoryDao.createEntity(dormitoryEntity);
        }

        return dormitoryEntity.getId();
    }

    @Override
    public PageInfo<DormitoryBean> getDormitoryList(Integer page, Integer rows,  List<HqlOperateVo> hqlOperateVos){
        PageInfo<DormitoryEntity> pageInfo = this.getList(page,rows, hqlOperateVos);
        PageInfo<DormitoryBean> result = new PageInfo<DormitoryBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<DormitoryBean> dormitoryBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(DormitoryEntity dormitoryEntity:pageInfo.getRows()){

                DormitoryBean bean = new DormitoryBean();
                setBeanValues(dormitoryEntity, bean);
                dormitoryBeanList.add(bean);
            }
            result.setRows(dormitoryBeanList);
        }

        return result;
    }

    @Override
    public List<DormitoryBean> getDormitoryList(List<HqlOperateVo> hqlOperateVos) {
        List<DormitoryEntity> dormitoryEntities = dormitoryDao.getList(hqlOperateVos);
        List<DormitoryBean> dormitoryBeanList = new ArrayList<>();

        for(DormitoryEntity dormitoryEntity:dormitoryEntities){

            DormitoryBean bean = new DormitoryBean();
            setBeanValues(dormitoryEntity, bean);
            dormitoryBeanList.add(bean);
        }
        return dormitoryBeanList;
    }

    @Override
    public List<StudentDormitoryExport> getStudentInDormitory(Integer dormitoryId) {
        //获取已经完成的订单的学生信息
        String sql = "SELECT\n" +
                "\tt1.chinese_name,t1.sex,t1.wei_xin,t1.birthday,t1.nationality,t1.passport_number,t1.id_number,t1.telephone,t1.email,t1.school_name\n" +
                "FROM\n" +
                "\tt_student_info t1\n" +
                "JOIN t_order_info t2 ON t1.id = t2.student_id\n" +
                "JOIN t_order_detail_info t3 ON t2.id = t3.order_id\n" +
                "WHERE\n" +
                "\tt3.dormitory_id = '"+ dormitoryId + "'\n" +
                "AND t2.h_delete = 0\n" +
                "AND t1.h_delete = 0\n" +
                "AND t2.`status` = '2'";

        List<Object[]> students = dormitoryDao.getBySql(sql);
        List<StudentDormitoryExport> studentDormitoryExports = new ArrayList<>();
        for(Object[] student : students){
            StudentDormitoryExport studentDormitoryExport = new StudentDormitoryExport();
            studentDormitoryExport.setChineseName((String)student[0]);
            studentDormitoryExport.setSex((String)student[1]);
            studentDormitoryExport.setWeiXin((String)student[2]);
            Date bir = (Date)student[3];
            studentDormitoryExport.setBirthday(DateUtil.format(new Date(bir.getTime()), "yyyy-MM-dd"));
            studentDormitoryExport.setNationality((String)student[4]);
            studentDormitoryExport.setPassportNumber((String)student[5]);
            studentDormitoryExport.setIdNumber((String)student[6]);
            studentDormitoryExport.setTelephone((String)student[7]);
            studentDormitoryExport.setEmail((String)student[8]);
            studentDormitoryExport.setSchoolName((String)student[9]);
            studentDormitoryExports.add(studentDormitoryExport);
        }
        return studentDormitoryExports;
    }

    @Override
    public DormitoryBean getDormitoryByProjectId(Integer projectId) {
        List<HqlOperateVo> hqlOperateVos = new ArrayList<>();
        hqlOperateVos.add(new HqlOperateVo("projectId","=",projectId.toString()));
        List<DormitoryEntity> dormitoryEntityList = dormitoryDao.getList(hqlOperateVos);
        if(dormitoryEntityList != null && dormitoryEntityList.size() == 1){
            DormitoryBean bean = new DormitoryBean();
            setBeanValues(dormitoryEntityList.get(0), bean);
            return bean;
        }
        return null;
    }

    private void setBeanValues(DormitoryEntity dormitoryEntity, DormitoryBean bean){

        try{
            BeanUtils.copyProperties(bean, dormitoryEntity);
            //设置projectname
            ProjectEntity projectEntity = projectDao.getEntity(dormitoryEntity.getProjectId());
            bean.setProjectName(projectEntity.getSieName());
            bean.setProjectCode(projectEntity.getCode());
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public void updateStudentCount(Integer id, Integer systemType, String sex, Integer flag, Integer orderType){
        if(!NumberUtil.isSignless(id)){
            return;
        }

        DormitoryEntity dormitoryEntity = this.get(id);
        if(dormitoryEntity == null){
            throw new RuntimeException("id 为 "+id+"宿舍为空，请检查参数");
        }
        if("男".equals(sex)){
            dormitoryEntity.setManNumber(dormitoryEntity.getManNumber()+flag);
        }else{
            dormitoryEntity.setWomanNumber(dormitoryEntity.getWomanNumber()+flag);
        }

        if(orderType == OrderType.USER.value()){
            if((dormitoryEntity.getWomanNumber()+dormitoryEntity.getManNumber()) >  dormitoryEntity.getMaxNumber()){
                throw new RuntimeException("宿舍["+dormitoryEntity.getName()+"]报名人数已超过规定人数，请检查参数");
            }
        }

        if(systemType == SystemType.SIE.value()){
            dormitoryEntity.setSieNumber(dormitoryEntity.getSieNumber()+flag);
        }else{
            dormitoryEntity.setTruNumber(dormitoryEntity.getTruNumber()+flag);
        }
        dormitoryEntity.setTotalNumber(dormitoryEntity.getWomanNumber()+dormitoryEntity.getManNumber());
        this.dormitoryDao.updateEntity(dormitoryEntity);
    }
}
