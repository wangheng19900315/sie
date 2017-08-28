package com.sie.service;

import com.sie.framework.entity.RoleEntity;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.RoleBean;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
public interface RoleService extends BaseService<RoleEntity, Integer> {


    public List<RoleEntity> getList();

    PageInfo<RoleBean> getRoleList(Integer page, Integer rows, Map<String, Object> parameter);

    /**
     * 更新权限
     * @param id
     * @param menuIds
     */
    void updateMenu(Integer id, String menuIds);

}
