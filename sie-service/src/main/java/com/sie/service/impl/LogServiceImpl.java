package com.sie.service.impl;

import com.sie.framework.dao.CouponDao;
import com.sie.framework.dao.LogDao;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.LogEntity;
import com.sie.service.CouponService;
import com.sie.service.LogService;
import com.sie.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by wangheng on 2017/8/9.
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<LogEntity,Integer> implements LogService {
    private LogDao logDao;

    @Autowired
    public LogServiceImpl(LogDao logDao){
        super(logDao);
        this.logDao = logDao;
    }

    public Integer saveOrUpdate(LogEntity logEntity){

        this.logDao.createEntity(logEntity);

        return logEntity.getId();
    }
}
