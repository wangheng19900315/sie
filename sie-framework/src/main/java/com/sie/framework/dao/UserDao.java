package com.sie.framework.dao;

import com.sie.framework.entity.UserEntity;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface UserDao extends GenericDao<UserEntity, Integer> {

    public List<UserEntity> getList(Integer page, Integer rows);

    public Integer getListCount(Integer page, Integer rows);
}
