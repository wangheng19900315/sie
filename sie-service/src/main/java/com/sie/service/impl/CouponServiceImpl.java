package com.sie.service.impl;

import com.sie.framework.dao.CouponDao;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.CrEntity;
import com.sie.service.CouponService;
import com.sie.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("couponService")
public class CouponServiceImpl extends BaseServiceImpl<CouponEntity,Integer> implements CouponService {
    private CouponDao couponDao;

    @Autowired
    public CouponServiceImpl(CouponDao couponDao){
        super(couponDao);
        this.couponDao = couponDao;
    }

    public Integer saveOrUpdate(CouponEntity couponEntity){

        if(NumberUtil.isSignless(couponEntity.getId())){
            CouponEntity oldCouponEntity = this.couponDao.getEntity(couponEntity.getId());
            oldCouponEntity.setName(couponEntity.getName());
            oldCouponEntity.setCode(couponEntity.getCode());
            oldCouponEntity.setRmbDiscount(couponEntity.getRmbDiscount());
            oldCouponEntity.setDollarDiscount(couponEntity.getDollarDiscount());
            oldCouponEntity.setCanadianDiscount(couponEntity.getCanadianDiscount());
            oldCouponEntity.setEnabled(couponEntity.getEnabled());
            this.couponDao.updateEntity(oldCouponEntity);
        }else{
            this.couponDao.createEntity(couponEntity);
        }

        return couponEntity.getId();
    }
}
