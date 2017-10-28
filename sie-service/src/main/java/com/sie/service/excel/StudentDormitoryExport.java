package com.sie.service.excel;

import com.sie.util.annotation.ExcelField;

import java.sql.Date;

/**
 * Created by x on 2017/8/12.
 */
public class StudentDormitoryExport {

//    private String userID;
//    private String email;
//    private String image;
//    private String imageName;//修改成用户ID.png或者jpg
//    private String lastName;
//    private String firstName;
    private String chineseName;
    private String sex;
    private String weiXin;
    private String birthday;
    private String nationality;
    private String passportNumber;
    private String idNumber;
    private String telephone;
    private String email;
    private String schoolName;
//    private String profession;
//    private String gpa;
//    private String graduationYear;


    @ExcelField(title="中文名", align=2, sort=1)
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @ExcelField(title="性别", align=2, sort=2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ExcelField(title="微信号", align=2, sort=3)
    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    @ExcelField(title="出生日期", align=2, sort=4)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @ExcelField(title="国籍", align=2, sort=5)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @ExcelField(title="护照号", align=2, sort=6)
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @ExcelField(title="身份证号", align=2, sort=7)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @ExcelField(title="联系电话", align=2, sort=8)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @ExcelField(title="邮箱", align=2, sort=9)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ExcelField(title="院校全名", align=2, sort=10)
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}