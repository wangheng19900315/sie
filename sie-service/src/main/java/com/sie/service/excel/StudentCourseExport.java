package com.sie.service.excel;

import com.sie.util.annotation.ExcelField;

/**
 * Created by x on 2017/8/12.
 */
public class StudentCourseExport {

    private String courseID;
    private String courseCode;
    private String courseEnglishName;
    private String projectCode;
    private String professor;
    private String courseTime;
    private Integer courseNumber;
    private String chineseName;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String weiXin;
    private String orderCode;
    private String sex;
    private String otherCourse1;
    private String otherCourse2;

    @ExcelField(title="课程ID", align=2, sort=1)
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @ExcelField(title="课程编码", align=2, sort=2)
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @ExcelField(title="课程名", align=2, sort=3)
    public String getCourseEnglishName() {
        return courseEnglishName;
    }

    public void setCourseEnglishName(String courseEnglishName) {
        this.courseEnglishName = courseEnglishName;
    }

    @ExcelField(title="所属项目", align=2, sort=4)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @ExcelField(title="教授", align=2, sort=5)
    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @ExcelField(title="上课时间", align=2, sort=6)
    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    @ExcelField(title="人数", align=2, sort=7)
    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    @ExcelField(title="中文名", align=2, sort=8)
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @ExcelField(title="名（拼音）", align=2, sort=9)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ExcelField(title="姓（拼音）", align=2, sort=10)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ExcelField(title="联系电话", align=2, sort=11)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @ExcelField(title="邮箱", align=2, sort=12)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ExcelField(title="微信号", align=2, sort=13)
    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    @ExcelField(title="订单号", align=2, sort=14)
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @ExcelField(title="性别", align=2, sort=15)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @ExcelField(title="其他课程1", align=2, sort=16)
    public String getOtherCourse1() {
        return otherCourse1;
    }

    public void setOtherCourse1(String otherCourse1) {
        this.otherCourse1 = otherCourse1;
    }

    @ExcelField(title="其他课程2", align=2, sort=17)
    public String getOtherCourse2() {
        return otherCourse2;
    }

    public void setOtherCourse2(String otherCourse2) {
        this.otherCourse2 = otherCourse2;
    }

}