package com.sie.service;

import com.sie.framework.entity.UserEntity;


/**
 * Created by wangheng on 2017/8/9.
 */
public interface UserService extends BaseService<UserEntity, Integer> {

    public UserEntity login(String userName, String password);

    public Integer updateRole(Integer id, Integer roleId);

}
