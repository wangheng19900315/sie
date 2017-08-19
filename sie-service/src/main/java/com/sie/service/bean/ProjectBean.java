package com.sie.service.bean;

import com.sie.framework.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    private CoursePrice[] siePrice;
    private CoursePrice[] truPrice;

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

    public CoursePrice[] getSiePrice() {
        return siePrice;
    }

    public void setSiePrice(CoursePrice[] siePrice) {
        this.siePrice = siePrice;
    }

    public CoursePrice[] getTruPrice() {
        return truPrice;
    }

    public void setTruPrice(CoursePrice[] truPrice) {
        this.truPrice = truPrice;
    }
}
