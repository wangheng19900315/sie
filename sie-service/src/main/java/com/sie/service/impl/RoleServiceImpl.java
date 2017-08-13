package com.sie.service.impl;

import com.sie.framework.dao.RoleDao;
import com.sie.framework.entity.RoleEntity;
import com.sie.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity,Integer> implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao){
        super(roleDao);
        this.roleDao = roleDao;
    }
}
