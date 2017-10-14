package com.sie.service.vo;

import com.sie.framework.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by x on 2017/8/12.
 */

public class ProjectVo {
    private String ids;
    private Integer id;
    private Integer system;
    private String code;
    private String name;
    private String mark;
    private Integer maxCourse;
    private String startTimeStr;
    private String endTimeStr;
    Map<String,List<CourseVo>> courseVos;
    List<DormitoryVo> dormitoryVos;


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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxCourse() {
        return maxCourse;
    }

    public void setMaxCourse(Integer maxCourse) {
        this.maxCourse = maxCourse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, List<CourseVo>> getCourseVos() {
        return courseVos;
    }

    public void setCourseVos(Map<String, List<CourseVo>> courseVos) {
        this.courseVos = courseVos;
    }

    public List<DormitoryVo> getDormitoryVos() {
        return dormitoryVos;
    }

    public void setDormitoryVos(List<DormitoryVo> dormitoryVos) {
        this.dormitoryVos = dormitoryVos;
    }
}
