package com.sie.framework.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_grade_send_info")
public class GradeSendEntity extends BaseEntity{
    private Integer studentId;
    private String sendStreet;//寄送街道
    private String sendCountry;//寄送县市
    private String sendProvince;//寄送州省
    private String sendPostCode;//寄送邮编
    private String sendPerson;
    private String sendTel;
    private String expressCompany;
    private String trackingNumber;
    private String comment;
    private Integer defaultSend;//是否是学生默认的寄送订单

    @Basic
    @Column(name = "student_id")
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "send_street")
    public String getSendStreet() {
        return sendStreet;
    }

    public void setSendStreet(String sendStreet) {
        this.sendStreet = sendStreet;
    }

    @Basic
    @Column(name = "send_country")
    public String getSendCountry() {
        return sendCountry;
    }

    public void setSendCountry(String sendCountry) {
        this.sendCountry = sendCountry;
    }

    @Basic
    @Column(name = "send_province")
    public String getSendProvince() {
        return sendProvince;
    }

    public void setSendProvince(String sendProvince) {
        this.sendProvince = sendProvince;
    }

    @Basic
    @Column(name = "send_post_code")
    public String getSendPostCode() {
        return sendPostCode;
    }

    public void setSendPostCode(String sendPostCode) {
        this.sendPostCode = sendPostCode;
    }

    @Basic
    @Column(name = "send_person")
    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    @Basic
    @Column(name = "send_tel")
    public String getSendTel() {
        return sendTel;
    }

    public void setSendTel(String sendTel) {
        this.sendTel = sendTel;
    }

    @Basic
    @Column(name = "express_company")
    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    @Basic
    @Column(name = "tracking_number")
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "default_send")
    public Integer getDefaultSend() {
        return defaultSend;
    }

    public void setDefaultSend(Integer defaultSend) {
        this.defaultSend = defaultSend;
    }
}
