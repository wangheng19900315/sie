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
    private Integer id;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String email;

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String image;

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String lastName;

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String firstName;

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String chineseName;

    @Basic
    @Column(name = "chinese_name")
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    private String sex;

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String weiXin;

    @Basic
    @Column(name = "wei_xin")
    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    private String school;

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    private Date birthday;

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private String nationality;

    @Basic
    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    private String passportNumber;

    @Basic
    @Column(name = "passport_ number")
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    private String idNumber;

    @Basic
    @Column(name = "id_number")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    private String telephone;

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private String sendStreet;

    @Basic
    @Column(name = "send_street")
    public String getSendStreet() {
        return sendStreet;
    }

    public void setSendStreet(String sendStreet) {
        this.sendStreet = sendStreet;
    }

    private String sendCity;

    @Basic
    @Column(name = "send_city")
    public String getSendCity() {
        return sendCity;
    }

    public void setSendCity(String sendCity) {
        this.sendCity = sendCity;
    }

    private String sendProvince;

    @Basic
    @Column(name = "send_province")
    public String getSendProvince() {
        return sendProvince;
    }

    public void setSendProvince(String sendProvince) {
        this.sendProvince = sendProvince;
    }

    private String sendCode;

    @Basic
    @Column(name = "send_code")
    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }

    private String sendUser;

    @Basic
    @Column(name = "send_user")
    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    private String sendTelephone;

    @Basic
    @Column(name = "send_telephone")
    public String getSendTelephone() {
        return sendTelephone;
    }

    public void setSendTelephone(String sendTelephone) {
        this.sendTelephone = sendTelephone;
    }

    private String emergencyPerson;

    @Basic
    @Column(name = "emergency_person")
    public String getEmergencyPerson() {
        return emergencyPerson;
    }

    public void setEmergencyPerson(String emergencyPerson) {
        this.emergencyPerson = emergencyPerson;
    }

    private String emergencyTelephone;

    @Basic
    @Column(name = "emergency_telephone")
    public String getEmergencyTelephone() {
        return emergencyTelephone;
    }

    public void setEmergencyTelephone(String emergencyTelephone) {
        this.emergencyTelephone = emergencyTelephone;
    }

    private String emergencyPersonRealtion;

    @Basic
    @Column(name = "emergency_person_realtion")
    public String getEmergencyPersonRealtion() {
        return emergencyPersonRealtion;
    }

    public void setEmergencyPersonRealtion(String emergencyPersonRealtion) {
        this.emergencyPersonRealtion = emergencyPersonRealtion;
    }

    private String profession;

    @Basic
    @Column(name = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    private String graduationYear;

    @Basic
    @Column(name = "graduation_year")
    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    private String gpa;

    @Basic
    @Column(name = "gpa")
    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    private String knowWay;

    @Basic
    @Column(name = "know_way")
    public String getKnowWay() {
        return knowWay;
    }

    public void setKnowWay(String knowWay) {
        this.knowWay = knowWay;
    }
}
