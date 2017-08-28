package com.sie.service.impl;

import com.sie.framework.dao.MenuDao;
import com.sie.framework.dao.RoleDao;
import com.sie.framework.entity.MenuEntity;
import com.sie.framework.entity.RoleEntity;
import com.sie.service.RoleService;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.RoleBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<RoleEntity> getList() {
        return this.roleDao.getList();
    }

    @Override
    public PageInfo<RoleBean> getRoleList(Integer page, Integer rows, Map<String, Object> parameter) {
        PageInfo<RoleEntity> pageInfo = this.getList(page,rows, parameter);
        PageInfo<RoleBean> result = new PageInfo<RoleBean>();
        result.setPage(pageInfo.getPage());
        result.setRecords(pageInfo.getRecords());
        result.setTotal(pageInfo.getTotal());

        List<RoleBean> roleBeanList = new ArrayList<>();
        if(pageInfo.getRows().size() > 0){
            for(RoleEntity roleEntity:pageInfo.getRows()){

                RoleBean bean = new RoleBean();
                BeanUtils.copyProperties(roleEntity, bean);

                String menuIds = "";
                for(MenuEntity menuEntity:roleEntity.getMenuList()){
                    menuIds += menuEntity.getId()+",";
                }
                bean.setMenuIds(menuIds);
                roleBeanList.add(bean);
            }
            result.setRows(roleBeanList);
        }
        return result;
    }

    @Override
    public void updateMenu(Integer id, String menuIds) {



        String[] strs = menuIds.split(",");

        RoleEntity roleEntity = this.get(id);

        if(roleEntity != null){
            roleEntity.getMenuList().clear();
            this.menuDao.updateEntity(roleEntity);

            for(String str:strs){
                MenuEntity menuEntity = this.menuDao.getEntity(Integer.parseInt(str));
                if(menuEntity != null){
                    roleEntity.getMenuList().add(menuEntity);
                }
            }

            this.roleDao.updateEntity(roleEntity);
        }

    }

}
