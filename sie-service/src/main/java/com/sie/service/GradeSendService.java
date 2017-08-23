package com.sie.service;

import com.sie.framework.entity.CourseEntity;
import com.sie.framework.entity.GradeSendEntity;
import com.sie.service.bean.CourseBean;
import com.sie.service.bean.GradeSendBean;
import com.sie.service.bean.PageInfo;

import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface GradeSendService extends BaseService<GradeSendEntity, Integer> {
    PageInfo<GradeSendBean> getGradeSendList(Integer page, Integer rows, Map<String, Object> parameter);
}
