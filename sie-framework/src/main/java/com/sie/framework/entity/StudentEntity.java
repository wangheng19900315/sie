package com.sie.framework.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_student_info")
public class StudentEntity extends BaseEntity{


    private String userID;
    private String userName;
    private String email;
    private String image;
    private String lastName;
    private String firstName;
    private String chineseName;
    private String sex;
    private String weiXin;
    private String schoolName;
    private Date birthday;
    private String nationality;
    private String passportNumber;
    private String idNumber;
    private String telephone;
    private String emergencyPerson;
    private String emergencyTelephone;
    private String emergencyPersonRealtion;
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

    private String password;



    @Basic
    @Column(name = "user_id")
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    @Basic
    @Column(name = "chinese_name")
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }



    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    @Basic
    @Column(name = "wei_xin")
    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    @Basic
    @Column(name = "school_name")
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    @Basic
    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }



    @Basic
    @Column(name = "passport_number")
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }


    @Basic
    @Column(name = "id_number")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }



    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    @Column(name = "send_province")
    public String getSendProvince() {
        return sendProvince;
    }

    public void setSendProvince(String sendProvince) {
        this.sendProvince = sendProvince;
    }

    @Basic
    @Column(name = "emergency_person")
    public String getEmergencyPerson() {
        return emergencyPerson;
    }

    public void setEmergencyPerson(String emergencyPerson) {
        this.emergencyPerson = emergencyPerson;
    }



    @Basic
    @Column(name = "emergency_telephone")
    public String getEmergencyTelephone() {
        return emergencyTelephone;
    }

    public void setEmergencyTelephone(String emergencyTelephone) {
        this.emergencyTelephone = emergencyTelephone;
    }



    @Basic
    @Column(name = "emergency_person_realtion")
    public String getEmergencyPersonRealtion() {
        return emergencyPersonRealtion;
    }

    public void setEmergencyPersonRealtion(String emergencyPersonRealtion) {
        this.emergencyPersonRealtion = emergencyPersonRealtion;
    }



    @Basic
    @Column(name = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }



    @Basic
    @Column(name = "graduation_year")
    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }



    @Basic
    @Column(name = "gpa")
    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
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

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
