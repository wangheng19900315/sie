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
    private String school;
    private Date birthday;
    private String nationality;
    private String passportNumber;
    private String idNumber;
    private String sendCity;
    private String sendProvince;
    private String telephone;
    private String sendCode;
    private String sendStreet;
    private String sendUser;
    private String sendTelephone;
    private String emergencyPerson;
    private String emergencyTelephone;
    private String emergencyPersonRealtion;
    private String profession;
    private String graduationYear;
    private String gpa;
    private String university;



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
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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
    @Column(name = "send_city")
    public String getSendCity() {
        return sendCity;
    }

    public void setSendCity(String sendCity) {
        this.sendCity = sendCity;
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
    @Column(name = "send_code")
    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }



    @Basic
    @Column(name = "send_user")
    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }



    @Basic
    @Column(name = "send_telephone")
    public String getSendTelephone() {
        return sendTelephone;
    }

    public void setSendTelephone(String sendTelephone) {
        this.sendTelephone = sendTelephone;
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
    @Column(name = "university")

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
