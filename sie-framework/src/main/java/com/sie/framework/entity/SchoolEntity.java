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
    private String address;

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
