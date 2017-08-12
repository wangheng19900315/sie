package com.sie.framework.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by wangheng on 2017/8/12.
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

    @Column(name = "h_version")
    private  Integer hversion;

    @Column(name = "h_delete")
    private Integer hdelete;

    @Column(name = "create_user_id")
    private Integer createUserId;

    @Column(name = "h_create_time")
    private Timestamp createTime;

    @Column(name = "h_update_time")
    private Timestamp updateTime;

    @Column(name = "modify_user_id")
    private Integer modifyUserId;

    public Integer getHversion() {
        return hversion;
    }

    public void setHversion(Integer hversion) {
        this.hversion = hversion;
    }

    public Integer getHdelete() {
        return hdelete;
    }

    public void setHdelete(Integer hdelete) {
        this.hdelete = hdelete;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}
