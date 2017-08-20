package com.sie.service.bean;

import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
public class CourseBean  extends BaseBean{
    private Integer system;
    private String systemName;
    private String chineseName;
    private String englishName;
    private Integer professorId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer projectId;
    private String sieCode;
    private Integer sieMaxStudent;
    private Integer sieTotalNumber;
    private String truCode;
    private Integer truMaxStudent;
    private Integer truTotalNumber;

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
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

    public Integer getSieMaxStudent() {
        return sieMaxStudent;
    }

    public void setSieMaxStudent(Integer sieMaxStudent) {
        this.sieMaxStudent = sieMaxStudent;
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

    public Integer getTruMaxStudent() {
        return truMaxStudent;
    }

    public void setTruMaxStudent(Integer truMaxStudent) {
        this.truMaxStudent = truMaxStudent;
    }

    public Integer getTruTotalNumber() {
        return truTotalNumber;
    }

    public void setTruTotalNumber(Integer truTotalNumber) {
        this.truTotalNumber = truTotalNumber;
    }
}