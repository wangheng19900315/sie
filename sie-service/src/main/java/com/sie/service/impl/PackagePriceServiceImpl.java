package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.CouponDao;
import com.sie.framework.dao.ProjectPriceDao;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.ProjectPriceEntity;
import com.sie.framework.type.SystemType;
import com.sie.service.PackagePriceService;
import com.sie.service.bean.PackagePriceBean;
import com.sie.service.bean.PageInfo;
import com.sie.util.NumberUtil;
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
@Service("packagePriceService")
public class PackagePriceServiceImpl extends BaseServiceImpl<ProjectPriceEntity,Integer> implements PackagePriceService {
    private ProjectPriceDao projectPriceDao;

    @Autowired
    public PackagePriceServiceImpl(ProjectPriceDao projectPriceDao){
        super(projectPriceDao);
        this.projectPriceDao = projectPriceDao;
    }

    @Override
    public Integer update(ProjectPriceEntity projectPriceEntity){

        if(NumberUtil.isSignless(projectPriceEntity.getId())){
            ProjectPriceEntity oldProjectPriceEntity = this.projectPriceDao.getEntity(projectPriceEntity.getId());
            oldProjectPriceEntity.setRmbPrice(projectPriceEntity.getRmbPrice());
            oldProjectPriceEntity.setDollarPrice(projectPriceEntity.getDollarPrice());
            oldProjectPriceEntity.setCanadianPrice(projectPriceEntity.getCanadianPrice());
            this.projectPriceDao.updateEntity(oldProjectPriceEntity);
            return oldProjectPriceEntity.getId();
        }else{
            return null;
        }

    }

    @Override
    public PageInfo<PackagePriceBean> getPriceList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVos) {
        //设置project内容为空
//        if(hqlOperateVos == null){
//            hqlOperateVos = new ArrayList<>();
//        }
//        hqlOperateVos.add(new HqlOperateVo("projectNumber","=","2"));
        PageInfo<ProjectPriceEntity> pageInfo = this.getList(page,rows, hqlOperateVos);
        PageInfo<PackagePriceBean> result = new PageInfo<PackagePriceBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<PackagePriceBean> courseBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(ProjectPriceEntity projectPriceEntity:pageInfo.getRows()){

                PackagePriceBean bean = new PackagePriceBean();
                setBeanValues(projectPriceEntity, bean);
                courseBeanList.add(bean);
            }
            result.setRows(courseBeanList);
        }

        return result;
    }

    private void setBeanValues(ProjectPriceEntity entity, PackagePriceBean bean){

        try{
            BeanUtils.copyProperties(bean, entity);
            if(NumberUtil.isSignless(bean.getSystem())){
                SystemType systemType = SystemType.valueOf(bean.getSystem());
                if(systemType != null){
                    bean.setSystemName(systemType.getName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public ProjectPriceEntity getEntityByCourse(Integer projectCount, Integer courseCount, Integer systType){
        List<HqlOperateVo> list = new ArrayList<>();
        list.add(new HqlOperateVo("projectNumber","=",projectCount+""));
        list.add(new HqlOperateVo("courseNumber","=",courseCount+""));
        list.add(new HqlOperateVo("system","=",systType+""));
        List<ProjectPriceEntity> priceEntities = this.getList(list);
        if(priceEntities != null && priceEntities.size() > 0){
            return priceEntities.get(0);
        }
        return null;
    }

    @Override
    public double getProjectPrice(int systemType,int projectNum, int courseNum) {
        List<HqlOperateVo> list = new  ArrayList<HqlOperateVo>();
        list.add(new HqlOperateVo("system", "=", systemType+""));
        list.add(new HqlOperateVo("projectNumber", "=", projectNum+""));
        list.add(new HqlOperateVo("courseNumber", "=", courseNum+""));
        List<ProjectPriceEntity> priceEntities = this.projectPriceDao.getList(list);
        if(priceEntities.size() == 1){
            return priceEntities.get(0).getRmbPrice();
        }
        return 0;
    }
}
