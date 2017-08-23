package com.sie.service.bean;

import com.sie.framework.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by x on 2017/8/12.
 */
public class DormitoryBean extends BaseBean{
    private String address;
    private String name;
    private Double price;
    private Integer projectId;
    private String projectName;
    private Integer womanNumber;
    private Integer manNumber;
    private Integer totalNumber;
    private Integer maxNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getWomanNumber() {
        return womanNumber;
    }

    public void setWomanNumber(Integer womanNumber) {
        this.womanNumber = womanNumber;
    }

    public Integer getManNumber() {
        return manNumber;
    }

    public void setManNumber(Integer manNumber) {
        this.manNumber = manNumber;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
