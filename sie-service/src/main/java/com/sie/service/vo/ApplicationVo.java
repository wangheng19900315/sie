package com.sie.service.vo;

import com.sie.framework.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by x on 2017/8/12.
 */
public class ApplicationVo{

    private Integer id;
    private String email;
    private String image;
    private String lastName;
    private String firstName;
    private String chineseName;
    private String sex;
    private String weiXin;
    private Date birthday;
    private String nationality;
    private String passportNumber;
    private String idNumber;
    private String telephone;

    private String schoolName;
    private String profession;
    private String graduationYear;
    private String gpa;

    //成绩单寄送地址
    private String sendCountry;//寄送县市
    private String sendProvince;//寄送州省
    private String sendStreet;
    private String sendPostCode;//寄送邮编
    private String sendPerson;
    private String sendTel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
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

    public String getSendStreet() {
        return sendStreet;
    }

    public void setSendStreet(String sendStreet) {
        this.sendStreet = sendStreet;
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
}
