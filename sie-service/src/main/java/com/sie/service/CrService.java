package com.sie.service;

import com.sie.framework.entity.CrEntity;


/**
 * Created by wangheng on 2017/8/9.
 */
public interface CrService extends BaseService<CrEntity, Integer> {

    CrEntity getCrByCode(String code);

}
