package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.GradeSendEntity;
import com.sie.service.bean.GradeSendBean;
import com.sie.service.bean.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface GradeSendService extends BaseService<GradeSendEntity, Integer> {
    PageInfo<GradeSendBean> getGradeSendList(Integer page, Integer rows,  List<HqlOperateVo> hqlOperateVos);

    List<GradeSendBean> getGradeSendList(List<HqlOperateVo> hqlOperateVos);
    String importBean(GradeSendBean bean);

    void updateStudentGradeSend(Integer studentId);

    GradeSendBean getGraseSendBean(Integer gradeSendId);
}
