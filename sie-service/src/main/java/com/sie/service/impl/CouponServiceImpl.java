package com.sie.service.impl;

import com.sie.framework.dao.CouponDao;
import com.sie.framework.entity.CouponEntity;
import com.sie.service.CouponService;
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
}
