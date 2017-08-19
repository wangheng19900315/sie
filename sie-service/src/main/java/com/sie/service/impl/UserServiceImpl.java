package com.sie.service.impl;

import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import com.sie.service.UserService;
import com.sie.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            oldUserEntity.setEmail(userEntity.getEmail());
            oldUserEntity.setTelephone(userEntity.getTelephone());
            this.userDao.updateEntity(oldUserEntity);
        }else{
            this.userDao.createEntity(userEntity);
        }

        return userEntity.getId();
    }

    @Override
    public UserEntity login(String userName, String password) {
        String hql = "from UserEntity where hdelete=0 and name='"+userName+"' and password='"+password+"'";
        List<UserEntity>  list = this.userDao.getList(hql);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
