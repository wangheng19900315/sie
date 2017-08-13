package com.sie.service.impl;

import com.sie.framework.dao.CrDao;
import com.sie.framework.entity.CrEntity;
import com.sie.service.CrService;
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
}
