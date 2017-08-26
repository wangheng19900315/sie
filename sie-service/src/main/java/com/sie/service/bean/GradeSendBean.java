package com.sie.service.bean;

import com.sie.framework.entity.BaseEntity;
import com.sie.util.annotation.ExcelField;

/**
 * Created by x on 2017/8/12.
 */
public class GradeSendBean extends BaseEntity{
    private Integer studentId;
    private String studentName;
    private String sendCountry;//寄送县市
    private String sendProvince;//寄送州省
    private String sendPostCode;//寄送邮编
    private String sendPerson;
    private String sendTel;
    private String expressCompany;
    private String trackingNumber;
    private String comment;

    @ExcelField(title="学生id", align=2, sort=1)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @ExcelField(title="学生id", align=2, sort=2)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ExcelField(title="寄送县／市", align=2, sort=4)
    public String getSendCountry() {
        return sendCountry;
    }

    public void setSendCountry(String sendCountry) {
        this.sendCountry = sendCountry;
    }

    @ExcelField(title="寄送州／省", align=2, sort=3)
    public String getSendProvince() {
        return sendProvince;
    }

    public void setSendProvince(String sendProvince) {
        this.sendProvince = sendProvince;
    }

    @ExcelField(title="寄送邮编", align=2, sort=5)
    public String getSendPostCode() {
        return sendPostCode;
    }

    public void setSendPostCode(String sendPostCode) {
        this.sendPostCode = sendPostCode;
    }

    @ExcelField(title="收货人", align=2, sort=6)
    public String getSendPerson() {
        return sendPerson;
    }


    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    @ExcelField(title="收货人电话", align=2, sort=7)
    public String getSendTel() {
        return sendTel;
    }

    public void setSendTel(String sendTel) {
        this.sendTel = sendTel;
    }

    @ExcelField(title="快递公司", align=2, sort=8)
    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    @ExcelField(title="快递单号", align=2, sort=9)
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
}
