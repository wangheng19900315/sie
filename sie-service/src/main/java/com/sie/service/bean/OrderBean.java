package com.sie.service.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x on 2017/8/12.
 */
public class OrderBean  extends BaseBean{

    //学生信息
    private String studentName;
    private String studentChineseName;
    private String schoolName;
    private String studentID;
    private String studentEmail;
    private String studentTel;
    private Integer studentId;
    private String weiXin;
    private String profession;
    private String identity;

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
    private Integer orderType;
    private String orderTypeName;
    private Double payMoney;
    private Integer payType;
    private String payTypeName;
    private Double crDiscount; //cr优惠金额
    private Double couponDiscount; //优惠卷优惠金额
    private Integer systemType;
    private String systemTypeName;
    private Timestamp orderTime;

    private String projectNames;
    private Integer courseNumber;
    private String dormitoryNames;
    private List<OrderDetailBean> orderDetailBean = new ArrayList<>();


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

    public List<OrderDetailBean> getOrderDetailBean() {
        return orderDetailBean;
    }

    public void setOrderDetailBean(List<OrderDetailBean> orderDetailBean) {
        this.orderDetailBean = orderDetailBean;
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getStudentTel() {
        return studentTel;
    }

    public void setStudentTel(String studentTel) {
        this.studentTel = studentTel;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getStudentChineseName() {
        return studentChineseName;
    }

    public void setStudentChineseName(String studentChineseName) {
        this.studentChineseName = studentChineseName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public String getProfession() {
        return profession;
    }

    public String getIdentity() {
        return identity;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public String getDormitoryNames() {
        return dormitoryNames;
    }

    public void setDormitoryNames(String dormitoryNames) {
        this.dormitoryNames = dormitoryNames;
    }
}
