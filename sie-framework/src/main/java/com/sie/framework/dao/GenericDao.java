package com.sie.framework.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface GenericDao<T, PK extends Serializable> {


    T get(PK id);

    List<T> findAll();


    void saveOrUpdate(T entity);

    void delete(PK id);

}
