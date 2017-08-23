package com.sie.service.bean;

import com.sie.util.annotation.ExcelField;

/**
 * Created by x on 2017/8/12.
 */
public class GradeBean {

    private String studentName;
    private Integer studentId;
    private Integer projectId;
    private String projectName;
    private Integer courseId;
    private String courseName;
    private Double grade;
    private Integer systemType;
    private String systemTypename;


    @ExcelField(title="学生名称", align=2, sort=1)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ExcelField(title="学生id", align=2, sort=2)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @ExcelField(title="项目id", align=2, sort=3)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @ExcelField(title="项目名称", align=2, sort=4)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @ExcelField(title="课程id", align=2, sort=5)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @ExcelField(title="课程名", align=2, sort=6)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @ExcelField(title="分数", align=2, sort=7)
    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @ExcelField(title="系统", align=2, sort=8)
    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    @ExcelField(title="系统名", align=2, sort=9)
    public String getSystemTypename() {
        return systemTypename;
    }

    public void setSystemTypename(String systemTypename) {
        this.systemTypename = systemTypename;
    }

    @Override
    public String toString() {
        return "GradeBean{" +
                "studentName='" + studentName + '\'' +
                ", studentId=" + studentId +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", grade=" + grade +
                ", systemType=" + systemType +
                ", systemTypename='" + systemTypename + '\'' +
                '}';
    }
}