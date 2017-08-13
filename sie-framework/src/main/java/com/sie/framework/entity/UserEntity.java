package com.sie.framework.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by wangheng on 2017/8/9.
 */

@Entity
@Table(name = "sys_user")
public class UserEntity extends BaseEntity{


    /**
     *
     */
    private static final long serialVersionUID = 6980093847795726310L;

    private Integer id;

    private String name;
    private String password;
    private Integer roleId;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
