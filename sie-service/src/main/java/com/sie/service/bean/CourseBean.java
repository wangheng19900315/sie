package com.sie.service.bean;

import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
public class CourseBean  extends BaseBean{
    private Integer system;
    private String courseID;
    private String systemName;
//    private String chineseName;
//    private String englishName;
    private Integer professorId;
    private String professorName;
    private String startTime;
//    private String startTimeFormat;
    private String endTime;
//    private String endTimeFormat;
    private Integer projectId;
    private String projectName;
    private String sieCode;
    private String sieChineseName;
    private String sieEnglishName;
    private Integer maxStudent;
    private Integer sieTotalNumber;
    private String truCode;
    private String truChineseName;
    private String truEnglishName;
    private Integer truTotalNumber;
    private Integer school;//校区
    private String schoolName;//校区
    private String classroom;//上课地点

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

//    public String getChineseName() {
//        return chineseName;
//    }
//
//    public void setChineseName(String chineseName) {
//        this.chineseName = chineseName;
//    }
//
//    public String getEnglishName() {
//        return englishName;
//    }
//
//    public void setEnglishName(String englishName) {
//        this.englishName = englishName;
//    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getSieCode() {
        return sieCode;
    }

    public void setSieCode(String sieCode) {
        this.sieCode = sieCode;
    }

    public Integer getSieTotalNumber() {
        return sieTotalNumber;
    }

    public void setSieTotalNumber(Integer sieTotalNumber) {
        this.sieTotalNumber = sieTotalNumber;
    }

    public String getTruCode() {
        return truCode;
    }

    public void setTruCode(String truCode) {
        this.truCode = truCode;
    }

    public Integer getTruTotalNumber() {
        return truTotalNumber;
    }

    public void setTruTotalNumber(Integer truTotalNumber) {
        this.truTotalNumber = truTotalNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(Integer maxStudent) {
        this.maxStudent = maxStudent;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSieChineseName() {
        return sieChineseName;
    }

    public void setSieChineseName(String sieChineseName) {
        this.sieChineseName = sieChineseName;
    }

    public String getSieEnglishName() {
        return sieEnglishName;
    }

    public void setSieEnglishName(String sieEnglishName) {
        this.sieEnglishName = sieEnglishName;
    }

    public String getTruChineseName() {
        return truChineseName;
    }

    public void setTruChineseName(String truChineseName) {
        this.truChineseName = truChineseName;
    }

    public String getTruEnglishName() {
        return truEnglishName;
    }

    public void setTruEnglishName(String truEnglishName) {
        this.truEnglishName = truEnglishName;
    }
}