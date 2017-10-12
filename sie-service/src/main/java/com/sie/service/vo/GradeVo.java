package com.sie.service.vo;

import com.sie.service.bean.BaseBean;
import com.sie.util.annotation.ExcelField;

/**
 * Created by x on 2017/8/12.
 */
public class GradeVo extends BaseBean{

    private String studentName;
    private String studentID;
    private Integer studentId;
    private Integer projectId;
    private String projectCode;
    private String projectName;
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private String grade;
    private Integer systemType;
    private String systemTypename;


    @ExcelField(title="学生名称", align=2, sort=1)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ExcelField(title="学生ID", align=2, sort=2)
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }


    @ExcelField(title="项目编号", align=2, sort=3)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @ExcelField(title="课程编号", align=2, sort=4)
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @ExcelField(title="课程名", align=2, sort=5)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @ExcelField(title="分数", align=2, sort=6)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getSystemTypename() {
        return systemTypename;
    }

    public void setSystemTypename(String systemTypename) {
        this.systemTypename = systemTypename;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}