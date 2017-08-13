package com.sie.service.impl;

import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import com.sie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserEntity,Integer> implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        super(userDao);
        this.userDao = userDao;
    }
}
