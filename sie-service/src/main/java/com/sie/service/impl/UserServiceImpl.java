package com.sie.service.impl;

import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.UserEntity;
import com.sie.service.UserService;
import com.sie.service.bean.PageInfo;
import com.sie.util.NumberUtil;
import com.sie.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

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
    public Integer saveOrUpdate(UserEntity entity) {

//        if(!NumberUtil.isSignless(entity.getId())){
//            this.userDao.createEntity(entity);
//
//        }else{
//           UserEntity oldEntity = this.get(entity.getId());
//            oldEntity.setName(entity.getName());
//            oldEntity.setPassword(entity.getPassword());
//
//            entity = oldEntity;
//            entity.setUpdateTime(new Timestamp(new Date().getTime()));
//        }
//
//        .saveOrUpdate(entity);
        return entity.getId();
    }

    @Override
    public void delete(Integer id) {
        UserEntity userEntity = this.get(id);
        userEntity.setHdelete(1);
        this.userDao.updateEntity(userEntity);
    }

    @Override
    public PageInfo<UserEntity> getList(Integer page, Integer rows, Map<String, Object> parameter) {
        if (!NumberUtil.isSignless(rows)) {
            rows = Integer.MAX_VALUE;
        }

        if (!NumberUtil.isSignless(page)) {
            page = 0;
        }
        Integer records = userDao.getListCount(page, rows);
        PageInfo<UserEntity> pageBean = new PageInfo<>(records,PageUtil.getPageTotal(records, rows));
        pageBean.setPage(PageUtil.getPageNow(page, pageBean.getTotal()));
        Integer firstResult = PageUtil.getFirstResult(pageBean.getPage(), rows);
        Integer maxResults = PageUtil.getMaxResults(rows);
        List<UserEntity> entityList = userDao.getList( firstResult, maxResults);

        pageBean.setRows(entityList);
        pageBean.setPage(page);
        pageBean.setRecords(records);
        return pageBean;
    }
}
