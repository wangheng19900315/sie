package com.sie.service;

import com.sie.framework.entity.DormitoryEntity;
import com.sie.service.bean.DormitoryBean;
import com.sie.service.bean.PageInfo;

import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface DormitoryService extends BaseService<DormitoryEntity, Integer> {

    PageInfo<DormitoryBean> getDormitoryList(Integer page, Integer rows, Map<String, Object> parameter);
}
