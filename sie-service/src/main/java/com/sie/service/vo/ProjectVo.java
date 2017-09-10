package com.sie.service.vo;

import com.sie.framework.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */

public class ProjectVo {
    private Integer id;
    private Integer system;
    private String code;
    private String sieName;
    private String truName;
    private String mark;
    private Integer sieMaxCourse;
    private Integer truMaxCourse;
    private String startTime;
    private String endTime;


    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
