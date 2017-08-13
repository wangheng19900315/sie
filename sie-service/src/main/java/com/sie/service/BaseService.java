package com.sie.service;

import com.sie.service.bean.PageInfo;
import com.sie.framework.entity.BaseEntity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/12.
 */
public interface BaseService<T extends BaseEntity, PK extends Serializable> {

    T get(PK id);

    Integer saveOrUpdate(T entity);

    void delete(PK id);

    PageInfo<T> getList(Integer page, Integer rows, Map<String, Object> parameter);
}
