package com.sie.service.bean;

import com.sie.framework.entity.BaseEntity;
import com.sie.framework.entity.OrderEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by x on 2017/8/12.
 */
public class OrderDetailBean extends BaseBean{
    private Integer projectId;
    private String projectName;
    private String   courseIds;
    private String custerNames;
    private Integer courseCount;
    private Integer dormitoryId;
    private String dormitoryName;
    private Integer orderDetailStatus;
    private String orderDetailStatusName;
    private Double total;

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

    public String getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }

    public String getCusterNames() {
        return custerNames;
    }

    public void setCusterNames(String custerNames) {
        this.custerNames = custerNames;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public Integer getOrderDetailStatus() {
        return orderDetailStatus;
    }

    public void setOrderDetailStatus(Integer orderDetailStatus) {
        this.orderDetailStatus = orderDetailStatus;
    }

    public String getOrderDetailStatusName() {
        return orderDetailStatusName;
    }

    public void setOrderDetailStatusName(String orderDetailStatusName) {
        this.orderDetailStatusName = orderDetailStatusName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
