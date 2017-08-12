package com.sie.service;

import com.sie.service.bean.PageInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/12.
 */
public interface BaseService<T, PK extends Serializable> {

    T get(PK id);

    void saveOrUpdate(T entity);

    void delete(PK id);

    PageInfo<T> getList(Integer page, Integer rows, Map<String, Object> parameter);
}
