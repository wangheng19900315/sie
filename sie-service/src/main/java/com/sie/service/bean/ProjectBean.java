package com.sie.service.bean;

import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
public class ProjectBean extends BaseBean{
    private Integer system;
    private String systemName;
    private String sieName;
    private String truName;
    private String mark;
    private Integer sieMaxCourse;
    private Integer truMaxCourse;
    private Timestamp startTime;
    private Timestamp endTime;
    private String startTimeFormat;
    private String endTimeFormat;

    private ProjectPriceBean[] siePrice;
    private ProjectPriceBean[] truPrice;

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

    public String getSieName() {
        return sieName;
    }

    public void setSieName(String sieName) {
        this.sieName = sieName;
    }

    public String getTruName() {
        return truName;
    }

    public void setTruName(String truName) {
        this.truName = truName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getSieMaxCourse() {
        return sieMaxCourse;
    }

    public void setSieMaxCourse(Integer sieMaxCourse) {
        this.sieMaxCourse = sieMaxCourse;
    }

    public Integer getTruMaxCourse() {
        return truMaxCourse;
    }

    public void setTruMaxCourse(Integer truMaxCourse) {
        this.truMaxCourse = truMaxCourse;
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

    public ProjectPriceBean[] getSiePrice() {
        return siePrice;
    }

    public void setSiePrice(ProjectPriceBean[] siePrice) {
        this.siePrice = siePrice;
    }

    public ProjectPriceBean[] getTruPrice() {
        return truPrice;
    }

    public void setTruPrice(ProjectPriceBean[] truPrice) {
        this.truPrice = truPrice;
    }

    public String getStartTimeFormat() {
        return startTimeFormat;
    }

    public void setStartTimeFormat(String startTimeFormat) {
        this.startTimeFormat = startTimeFormat;
    }

    public String getEndTimeFormat() {
        return endTimeFormat;
    }

    public void setEndTimeFormat(String endTimeFormat) {
        this.endTimeFormat = endTimeFormat;
    }
}
