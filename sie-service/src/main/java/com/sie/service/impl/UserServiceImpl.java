package com.sie.service.impl;

import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import com.sie.service.UserService;
import com.sie.service.bean.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity get(Integer id) {
        return null;
    }

    @Override
    public void saveOrUpdate(UserEntity entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public PageInfo<UserEntity> getList(Integer page, Integer rows, Map<String, Object> parameter) {
        parameter.put("start", (page - 1) * rows);
        parameter.put("pageCount", rows);
        //判断是否是管理员

//        List<UserEntity> list = UserEntity.getMonitorList(parameter);
//        int count = monitorMapper.getMonitorCount(parameter);

//        PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(count, rows);

        return null;
    }
}
