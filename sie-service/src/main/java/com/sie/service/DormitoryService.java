package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.DormitoryEntity;
import com.sie.service.bean.DormitoryBean;
import com.sie.service.bean.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface DormitoryService extends BaseService<DormitoryEntity, Integer> {

    PageInfo<DormitoryBean> getDormitoryList(Integer page, Integer rows,  List<HqlOperateVo> hqlOperateVos);

    List<DormitoryBean> getDormitoryList(List<HqlOperateVo> hqlOperateVos);

    DormitoryBean getDormitoryByProjectId(Integer projectId);

    /**
     * 更新宿舍人数
     * @param id
     * @param sex 表示性别
     * @param flag 1，表示人数加1； -1表示人数减1
     */
    public void updateStudentCount(Integer id, Integer systemType, String sex, Integer flag);
}
