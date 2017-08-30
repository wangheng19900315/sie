package com.sie.service.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x on 2017/8/12.
 */
public class RefundOrderBean extends BaseBean{

    //学生信息
    private Integer studentId;

    private Double money;
    private Integer crId;
    private Integer couponId;
    private String remark;
    private String orderType;
    private Double payMoney;
    private Integer payType;
    private Integer systemType;
    private String projectNames;
    private List<OrderDetailBean> orderDetailBeen = new ArrayList<>();

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getCrId() {
        return crId;
    }

    public void setCrId(Integer crId) {
        this.crId = crId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public List<OrderDetailBean> getOrderDetailBeen() {
        return orderDetailBeen;
    }

    public void setOrderDetailBeen(List<OrderDetailBean> orderDetailBeen) {
        this.orderDetailBeen = orderDetailBeen;
    }
}
