package com.sie.service.excel;

import com.sie.util.annotation.ExcelField;

import java.sql.Time;
import java.util.Date;

/**
 * Created by x on 2017/8/12.
 */
public class OrderImport {

    private String studentID;
    private String projectCode;
    private Integer courseNumber;
    private String courseIDs;
    private String systemTypeName;
    private String cr;
    private String coupon;
    private String remark;
    private String payInfo;
    private Double payMoney;
    private String status;
    private String payTypeName;
    private Date orderDate;
    private Date payDate;
    private Time payTime;

    @ExcelField(title="学生ID", align=2, sort=1)
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @ExcelField(title="项目编号", align=2, sort=2)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @ExcelField(title="课程数", align=2, sort=3)
    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    @ExcelField(title="课程ID", align=2, sort=4)
    public String getCourseIDs() {
        return courseIDs;
    }

    public void setCourseIDs(String courseIDs) {
        this.courseIDs = courseIDs;
    }

    @ExcelField(title="系统名称", align=2, sort=5)
    public String getSystemTypeName() {
        return systemTypeName;
    }

    public void setSystemTypeName(String systemTypeName) {
        this.systemTypeName = systemTypeName;
    }

    @ExcelField(title="CR推荐码", align=2, sort=6)
    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    @ExcelField(title="优惠码", align=2, sort=7)
    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    @ExcelField(title="备注", align=2, sort=8)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ExcelField(title="支付信息", align=2, sort=9)
    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    @ExcelField(title="金额", align=2, sort=10)
    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    @ExcelField(title="状态", align=2, sort=11)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ExcelField(title="支付类型", align=2, sort=12)
    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    @ExcelField(title="下单日期", align=2, sort=13)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @ExcelField(title="支付日期", align=2, sort=14)
    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @ExcelField(title="支付时间", align=2, sort=15)
    public Time getPayTime() {
        return payTime;
    }

    public void setPayTime(Time payTime) {
        this.payTime = payTime;
    }
}