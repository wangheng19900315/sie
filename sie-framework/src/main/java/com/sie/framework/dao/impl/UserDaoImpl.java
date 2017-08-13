package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<UserEntity, Integer> implements UserDao {

}
