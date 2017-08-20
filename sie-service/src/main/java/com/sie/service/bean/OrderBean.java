package com.sie.service.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x on 2017/8/12.
 */
public class OrderBean  extends BaseBean{

    private String code;
    private Double money;
    private Double discount;
    private Integer crId;
    private String crnName;
    private Integer couponId;
    private String couponName;
    private Integer status;
    private String statusName;
    private Timestamp payTime;
    private String remark;
    private String orderTypeName;
    private Double payMoney;
    private String studentName;
    private Integer studentId;
    private Double crDiscount; //cr优惠金额
    private Double couponDiscount; //优惠卷优惠金额
    private Integer systemType;
    private String systemTypeName;



    private List<OrderDetailBean> orderDetailBeen = new ArrayList<>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public List<OrderDetailBean> getOrderDetailBeen() {
        return orderDetailBeen;
    }

    public void setOrderDetailBeen(List<OrderDetailBean> orderDetailBeen) {
        this.orderDetailBeen = orderDetailBeen;
    }

    public String getCrnName() {
        return crnName;
    }

    public void setCrnName(String crnName) {
        this.crnName = crnName;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Double getCrDiscount() {
        return crDiscount;
    }

    public void setCrDiscount(Double crDiscount) {
        this.crDiscount = crDiscount;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getSystemTypeName() {
        return systemTypeName;
    }

    public void setSystemTypeName(String systemTypeName) {
        this.systemTypeName = systemTypeName;
    }
}
