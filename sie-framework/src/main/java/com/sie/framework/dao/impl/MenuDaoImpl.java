package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.MenuDao;
import com.sie.framework.entity.MenuEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("menuDao")
public class MenuDaoImpl extends GenericDaoImpl<MenuEntity, Integer> implements MenuDao {

}
