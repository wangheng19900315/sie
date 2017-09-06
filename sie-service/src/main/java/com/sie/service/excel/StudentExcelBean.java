package com.sie.service.excel;

import com.sie.service.bean.BaseBean;
import com.sie.util.annotation.ExcelField;

import java.sql.Date;

/**
 * Created by x on 2017/8/12.
 */
public class StudentExcelBean {

    private String userID;
    private String email;
    private String image;
    private String imageName;//修改成用户ID.png或者jpg
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
    private String gpa;
    private String graduationYear;

    @ExcelField(title="用户ID", align=2, sort=1)
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @ExcelField(title="用户邮箱/用户名", align=2, sort=2)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @ExcelField(title="姓/Last Name", align=2, sort=3)
    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ExcelField(title="名/First Name", align=2, sort=4)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ExcelField(title="中文名", align=2, sort=5)
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @ExcelField(title="性别", align=2, sort=6)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ExcelField(title="微信号", align=2, sort=7)
    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    @ExcelField(title="出生日期", align=2, sort=8)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ExcelField(title="国籍", align=2, sort=9)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @ExcelField(title="护照号", align=2, sort=10)
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @ExcelField(title="身份证号", align=2, sort=11)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @ExcelField(title="国内联系电话", align=2, sort=12)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @ExcelField(title="在读大学", align=2, sort=13)
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @ExcelField(title="专业", align=2, sort=14)
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @ExcelField(title="GPA", align=2, sort=15)
    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    @ExcelField(title="预计毕业年份", align=2, sort=16)
    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    @ExcelField(title="照片", align=2, sort=17)
    public String getImageName() {
        imageName = userID + image.substring(image.lastIndexOf('.'));
        return imageName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}