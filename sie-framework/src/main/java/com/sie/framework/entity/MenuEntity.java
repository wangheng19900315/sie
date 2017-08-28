package com.sie.framework.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_menu_info")
public class MenuEntity extends BaseEntity{
//    private Integer id;
    private String name;
    private String action;

    private Integer parentId;



    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
