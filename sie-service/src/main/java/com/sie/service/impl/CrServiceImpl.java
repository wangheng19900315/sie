package com.sie.service.impl;

import com.sie.framework.dao.CrDao;
import com.sie.framework.entity.CrEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.service.CrService;
import com.sie.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("crService")
public class CrServiceImpl extends BaseServiceImpl<CrEntity,Integer> implements CrService {
    private CrDao crDao;

    @Autowired
    public CrServiceImpl(CrDao crDao){
        super(crDao);
        this.crDao = crDao;
    }

    public Integer saveOrUpdate(CrEntity crEntity){
        //逻辑判断 总数量必须大于等于使用数量
        if(crEntity.getTotal() < crEntity.getUsed()){
            return null;
        }
        if(NumberUtil.isSignless(crEntity.getId())){
            CrEntity oldCrEntity = this.crDao.getEntity(crEntity.getId());
            oldCrEntity.setPersonName(crEntity.getPersonName());
            oldCrEntity.setTotal(crEntity.getTotal());
            oldCrEntity.setPrice(crEntity.getPrice());
            this.crDao.updateEntity(crEntity);
        }else{
            crEntity.setUsed(0);
            this.crDao.createEntity(crEntity);
        }

        return crEntity.getId();
    }
}
