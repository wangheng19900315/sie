package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.ProjectPriceEntity;
import com.sie.service.bean.CourseBean;
import com.sie.service.bean.PackagePriceBean;
import com.sie.service.bean.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface PackagePriceService extends BaseService<ProjectPriceEntity, Integer> {
    /**
     * 对2门课程进行维护目前只进行修改价格操作
     */
    Integer update(ProjectPriceEntity projectPriceEntity);

    PageInfo<PackagePriceBean> getPriceList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVos);

    public ProjectPriceEntity getEntityByCourse(Integer projectCount, Integer courseCount, Integer systType);
}
