package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.MenuDao;
import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.SysMenuEntity;
import com.sie.framework.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("menuDao")
public class MenuDaoImpl extends GenericDaoImpl<SysMenuEntity, Integer> implements MenuDao {

}
