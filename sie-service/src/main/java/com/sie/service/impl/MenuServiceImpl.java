package com.sie.service.impl;

import com.sie.framework.dao.MenuDao;
import com.sie.framework.dao.UserDao;
import com.sie.framework.entity.SysMenuEntity;
import com.sie.service.MenuService;
import com.sie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<SysMenuEntity,Integer> implements MenuService {
    private MenuDao menuDao;

    @Autowired
    public MenuServiceImpl(MenuDao menuDao){
        super(menuDao);
        this.menuDao = menuDao;
    }
}
