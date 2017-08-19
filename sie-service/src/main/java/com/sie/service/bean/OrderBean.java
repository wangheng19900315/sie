package com.sie.service.bean;

import com.sie.framework.entity.BaseEntity;
import com.sie.framework.entity.OrderDetailEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by x on 2017/8/12.
 */
public class OrderBean  {

    private String code;
    private Double money;
    private Double discount;
    private Integer crId;
    private Integer couponId;
    private String couponName;
    private Integer courseNumber;
    private Byte status;
    private String statsName;
    private Timestamp payTime;
    private String remark;


    private List<OrderDetailBean> orderDetailBeen;


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

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getStatsName() {
        return statsName;
    }

    public void setStatsName(String statsName) {
        this.statsName = statsName;
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
}
