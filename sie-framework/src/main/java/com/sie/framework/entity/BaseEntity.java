package com.sie.framework.entity;


import java.sql.Timestamp;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wangheng on 2017/8/12.
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

    private Integer id;

    private  Integer hversion=0;


    private Integer hdelete=0;


    private Integer createUserId;


    private Timestamp createTime;


    private Timestamp updateTime;


    private Integer modifyUserId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "h_version")
    public Integer getHversion() {
        return hversion;
    }

    public void setHversion(Integer hversion) {
        this.hversion = hversion;
    }

    @Column(name = "h_delete")
    public Integer getHdelete() {
        return hdelete;
    }

    public void setHdelete(Integer hdelete) {
        this.hdelete = hdelete;
    }

    @Column(name = "create_user_id")
    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @Column(name = "h_create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "h_update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "modify_user_id")
    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}
