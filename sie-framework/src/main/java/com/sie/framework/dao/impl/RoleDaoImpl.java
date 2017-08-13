package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.RoleDao;
import com.sie.framework.entity.RoleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<RoleEntity,Integer> implements RoleDao {

}
