package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by wangheng on 2017/8/9.
 */

@Entity
@Table(name = "t_user_info")
public class UserEntity extends BaseEntity{


    /**
     *
     */
    private static final long serialVersionUID = 6980093847795726310L;

    private Integer id;

    private String name;
    private String password;
    private String email;
    private String telephone;
    private Integer roleId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = true, columnDefinition = "COMMENT '角色id'")
    @Where(clause = "h_delete=0")
    private RoleEntity roleEntity;


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

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
