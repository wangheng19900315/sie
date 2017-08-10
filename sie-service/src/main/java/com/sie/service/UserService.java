package com.sie.service;

import com.sie.framework.entity.UserEntity;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface UserService {

    UserEntity  load(String id);

    UserEntity get(String id);

    List<UserEntity> findAll();

    void persist(UserEntity entity);

    String save(UserEntity entity);

    void saveOrUpdate(UserEntity entity);

    void delete(String id);

    void flush();
}
