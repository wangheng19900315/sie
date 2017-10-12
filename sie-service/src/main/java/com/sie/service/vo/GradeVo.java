package com.sie.service.vo;

import com.sie.service.bean.BaseBean;
import com.sie.util.annotation.ExcelField;

/**
 * Created by x on 2017/8/12.
 */
public class GradeVo{

    private String term;
    private String grade;
    private String courseCode;
    private String courseChineseName;
    private String courseEnglishName;


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseChineseName() {
        return courseChineseName;
    }

    public void setCourseChineseName(String courseChineseName) {
        this.courseChineseName = courseChineseName;
    }

    public String getCourseEnglishName() {
        return courseEnglishName;
    }

    public void setCourseEnglishName(String courseEnglishName) {
        this.courseEnglishName = courseEnglishName;
    }
}