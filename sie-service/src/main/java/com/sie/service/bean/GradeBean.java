package com.sie.service.bean;

/**
 * Created by x on 2017/8/12.
 */
public class GradeBean extends BaseBean{

    private String studentName;
    private Integer studentId;
    private Integer projectId;
    private String projectName;
    private Integer courseId;
    private String courseName;
    private Double grade;
    private Integer systemType;
    private String systemTypename;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
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