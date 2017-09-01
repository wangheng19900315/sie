package com.sie.service.impl;

import com.sie.framework.dao.RoleDao;
import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.RoleEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.framework.help.ApplicationHelp;
import com.sie.service.UserService;
import com.sie.util.Md5Util;
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
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        super(userDao);
        this.userDao = userDao;
    }


    public Integer saveOrUpdate(UserEntity userEntity){
        if(NumberUtil.isSignless(userEntity.getId())){
            UserEntity oldUserEntity = this.userDao.getEntity(userEntity.getId());
            oldUserEntity.setName(userEntity.getName());
            oldUserEntity.setPassword(Md5Util.getMD5(userEntity.getPassword(), ApplicationHelp.MD5_SHA1));
            oldUserEntity.setEmail(userEntity.getEmail());
            oldUserEntity.setTelephone(userEntity.getTelephone());
            this.userDao.updateEntity(oldUserEntity);
        }else{
            userEntity.setPassword(Md5Util.getMD5(userEntity.getPassword(), ApplicationHelp.MD5_SHA1));
            this.userDao.createEntity(userEntity);
        }

        return userEntity.getId();
    }


    public Integer updateRole(Integer id, Integer roleId){
        Integer result = 0;
        if(NumberUtil.isSignless(id)){
            UserEntity userEntity = this.userDao.getEntity(id);
            RoleEntity roleEntity = this.roleDao.getEntity(roleId);
            userEntity.setRoleEntity(roleEntity);
            this.userDao.updateEntity(userEntity);

            result = userEntity.getId();
        }

        return result;
    }

    @Override
    public UserEntity login(String userName, String password) {
        password = Md5Util.getMD5(password, ApplicationHelp.MD5_SHA1);
        String hql = "from UserEntity where hdelete=0 and name='"+userName+"' and password='"+password+"'";
        List<UserEntity>  list = this.userDao.getList(hql);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
