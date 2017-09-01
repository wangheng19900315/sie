package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.DormitoryDao;
import com.sie.framework.dao.ProjectDao;
import com.sie.framework.entity.DormitoryEntity;
import com.sie.framework.entity.ProjectEntity;
import com.sie.service.DormitoryService;
import com.sie.service.bean.DormitoryBean;
import com.sie.service.bean.PageInfo;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
