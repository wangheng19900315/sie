package com.sie.service.excel;

import com.sie.service.bean.BaseBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.util.annotation.ExcelField;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x on 2017/8/12.
 */
public class OrderExcelBean extends BaseBean{

    //学生信息

    @ExcelField(title="订单号", align=2, sort=1)
    private String code;
    @ExcelField(title="学生编号", align=2, sort=2)
    private String studentID;
    @ExcelField(title="详情-项目ID", align=2, sort=3)
    private String projectNames;
    @ExcelField(title="详情-课程数", align=2, sort=4)
    private Integer courseNumber;
    @ExcelField(title="详情-课程", align=2, sort=4)
    private String courseNames;
    @ExcelField(title="详情-住宿ID", align=2, sort=3)
    private String dormitoryNames;
    @ExcelField(title="CR推荐码", align=2, sort=5)
    private String crnName;
    @ExcelField(title="优惠码", align=2, sort=6)
    private String couponName;
    @ExcelField(title="支付状态", align=2, sort=7)
    private String statusName;
    @ExcelField(title="支付时间", align=2, sort=8)
    private String payTimeString;
    @ExcelField(title="下单时间", align=2, sort=9)
    private String createTimeString;
    @ExcelField(title="备注", align=2, sort=10)
    private String remark;
    @ExcelField(title="订单类型", align=2, sort=11)
    private Integer orderType;
    @ExcelField(title="支付金额", align=2, sort=12)
    private Double payMoney;
    @ExcelField(title="支付类型", align=2, sort=13)
    private String payTypeName;
    @ExcelField(title="所属系统", align=2, sort=14)
    private String systemTypeName;
    @ExcelField(title="学生名称", align=2, sort=15)
    private String studentName;







    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCrnName() {
        return crnName;
    }

    public void setCrnName(String crnName) {
        this.crnName = crnName;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPayTimeString() {
        return payTimeString;
    }

    public void setPayTimeString(String payTimeString) {
        this.payTimeString = payTimeString;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getSystemTypeName() {
        return systemTypeName;
    }

    public void setSystemTypeName(String systemTypeName) {
        this.systemTypeName = systemTypeName;
    }

    public String getCreateTimeString() {
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public String getDormitoryNames() {
        return dormitoryNames;
    }

    public void setDormitoryNames(String dormitoryNames) {
        this.dormitoryNames = dormitoryNames;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(String courseNames) {
        this.courseNames = courseNames;
    }
}
