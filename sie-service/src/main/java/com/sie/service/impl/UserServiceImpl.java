package com.sie.service.impl;

import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import com.sie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity load(String id) {
        return userDao.load(id);
    }

    @Override
    public UserEntity get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    public void persist(UserEntity entity) {
        userDao.persist(entity);
    }

    @Override
    public String save(UserEntity entity) {
        return userDao.save(entity);
    }

    @Override
    public void saveOrUpdate(UserEntity entity) {
        userDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void flush() {
        userDao.flush();
    }
}
