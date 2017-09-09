package com.sie.service;

import com.sie.framework.base.HqlOperateVo;
import com.sie.service.bean.PageInfo;
import com.sie.framework.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/12.
 */
public interface BaseService<T extends BaseEntity, PK extends Serializable> {

    T get(PK id);

    Integer saveOrUpdate(T entity);

    void delete(PK id);

    PageInfo<T> getList(Integer page, Integer rows, List<HqlOperateVo> hqlOperateVos);

    List<T> getList(List<HqlOperateVo> hqlOperateVos);

    List<T> getList(String hql);
}
