package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.CouponDao;
import com.sie.framework.entity.CouponEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("couponDao")
public class CouponDaoImpl extends GenericDaoImpl<CouponEntity, Integer> implements CouponDao {

}
