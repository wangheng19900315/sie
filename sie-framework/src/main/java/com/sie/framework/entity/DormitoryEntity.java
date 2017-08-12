package com.sie.framework.entity;

import javax.persistence.*;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "dormitory")
public class DormitoryEntity extends BaseEntity{
    private Integer id;
    private String address;
    private Integer projectId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
