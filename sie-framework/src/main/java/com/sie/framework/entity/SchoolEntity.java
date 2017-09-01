package com.sie.framework.entity;

import javax.persistence.*;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_school_info")
public class SchoolEntity extends BaseEntity {
//    private Integer id;
    private String name;
    private String nationality;
    private String province;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
