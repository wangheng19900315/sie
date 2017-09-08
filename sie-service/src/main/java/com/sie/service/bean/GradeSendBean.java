package com.sie.service.bean;

import com.sie.framework.entity.BaseEntity;
import com.sie.util.annotation.ExcelField;

/**
 * Created by x on 2017/8/12.
 */
public class GradeSendBean extends BaseEntity{

    private Integer studentId;
    @ExcelField(title="学生ID", align=2, sort=1)
    private String userID;
    private String studentName;
    @ExcelField(title="成绩单街道", align=2, sort=2)
    private String sendStreet;//寄送街道
    @ExcelField(title="寄送县／市", align=2, sort=3)
    private String sendCountry;//寄送县市
    @ExcelField(title="寄送州／省", align=2, sort=4)
    private String sendProvince;//寄送州省
    @ExcelField(title="寄送邮编", align=2, sort=5)
    private String sendPostCode;//寄送邮编
    @ExcelField(title="收货人", align=2, sort=6)
    private String sendPerson;
    @ExcelField(title="收货人电话", align=2, sort=7)
    private String sendTel;
    @ExcelField(title="快递公司", align=2, sort=8)
    private String expressCompany;
    @ExcelField(title="快递单号", align=2, sort=9)
    private String trackingNumber;
    private String comment;


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getSendCountry() {
        return sendCountry;
    }

    public void setSendCountry(String sendCountry) {
        this.sendCountry = sendCountry;
    }


    public String getSendProvince() {
        return sendProvince;
    }

    public void setSendProvince(String sendProvince) {
        this.sendProvince = sendProvince;
    }


    public String getSendPostCode() {
        return sendPostCode;
    }

    public void setSendPostCode(String sendPostCode) {
        this.sendPostCode = sendPostCode;
    }


    public String getSendPerson() {
        return sendPerson;
    }


    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }


    public String getSendTel() {
        return sendTel;
    }

    public void setSendTel(String sendTel) {
        this.sendTel = sendTel;
    }


    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }


    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSendStreet() {
        return sendStreet;
    }

    public void setSendStreet(String sendStreet) {
        this.sendStreet = sendStreet;
    }
}
