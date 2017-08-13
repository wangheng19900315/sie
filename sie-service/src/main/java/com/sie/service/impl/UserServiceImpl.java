package com.sie.service.impl;

import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import com.sie.service.UserService;
import com.sie.util.NumberUtil;
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


    public Integer saveOrUpdate(UserEntity userEntity){
        if(NumberUtil.isSignless(userEntity.getId())){
            UserEntity oldUserEntity = this.userDao.getEntity(userEntity.getId());
            oldUserEntity.setName(userEntity.getName());
            oldUserEntity.setPassword(userEntity.getPassword());
            this.userDao.updateEntity(userEntity);
        }else{
            this.userDao.createEntity(userEntity);
        }

        return userEntity.getId();
    }

}
