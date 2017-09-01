package com.sie.service.bean;

import com.sie.framework.entity.BaseEntity;
import com.sie.util.annotation.ExcelField;

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
    private String code;
    private Double price;
    private Integer projectId;
    private String projectName;
    private String projectCode;
    private Integer womanNumber;
    private Integer manNumber;
    private Integer totalNumber;
    private Integer maxNumber;
    private Integer sieNumber;
    private Integer truNumber;

    @ExcelField(title="住宿ID", align=2, sort=1)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ExcelField(title="住宿名称", align=2, sort=2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title="限制人数", align=2, sort=3)
    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    @ExcelField(title="女生人数", align=2, sort=4)
    public Integer getWomanNumber() {
        return womanNumber;
    }

    public void setWomanNumber(Integer womanNumber) {
        this.womanNumber = womanNumber;
    }

    @ExcelField(title="男生人数", align=2, sort=5)
    public Integer getManNumber() {
        return manNumber;
    }

    public void setManNumber(Integer manNumber) {
        this.manNumber = manNumber;
    }

    @ExcelField(title="SIE人数", align=2, sort=6)
    public Integer getSieNumber() {
        return sieNumber;
    }

    public void setSieNumber(Integer sieNumber) {
        this.sieNumber = sieNumber;
    }

    @ExcelField(title="TRU人数", align=2, sort=7)
    public Integer getTruNumber() {
        return truNumber;
    }

    public void setTruNumber(Integer truNumber) {
        this.truNumber = truNumber;
    }

    @ExcelField(title="总人数", align=2, sort=8)
    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


}
