package com.sie.framework.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_role_info")
public class RoleEntity extends BaseEntity{
//    private Integer id;
    private String name;

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }



    private List<MenuEntity> menuList = new ArrayList<>();
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name="t_role_menu_info", joinColumns={@JoinColumn(name="role_id")}
            , inverseJoinColumns={@JoinColumn(name="menu_id")})
    public List<MenuEntity> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuEntity> menuList) {
        this.menuList = menuList;
    }
}
