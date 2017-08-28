package com.sie.service.bean;

import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
public class RoleBean extends BaseBean{

    private String name;

    private String menuIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
