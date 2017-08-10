package com.sie.framework.dao.impl;

import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public UserEntity load(String id) {
        return (UserEntity) this.getCurrentSession().load(UserEntity.class, id);
    }

    @Override
    public UserEntity get(String id) {
        return (UserEntity) this.getCurrentSession().get(UserEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> acctUsers = this.getCurrentSession().createQuery("from UserEntity").setCacheable(true).list();
        return acctUsers;
    }

    @Override
    public void persist(UserEntity entity) {
        this.getCurrentSession().persist(entity);

    }

    @Override
    public String save(UserEntity entity) {
        return (String) this.getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(UserEntity entity) {
        this.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(String id) {
        UserEntity entity = this.load(id);
        this.getCurrentSession().delete(entity);
    }

    @Override
    public void flush() {
        this.getCurrentSession().flush();

    }
}
