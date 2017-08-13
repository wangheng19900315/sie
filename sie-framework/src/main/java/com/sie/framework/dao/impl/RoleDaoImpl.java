package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.RoleDao;
import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.SysRoleEntity;
import com.sie.framework.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<SysRoleEntity,Integer> implements RoleDao {

}
