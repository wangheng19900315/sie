package com.sie.service.bean;

import com.sie.util.annotation.ExcelField;

import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
public class ProjectBean extends BaseBean{
    private Integer system;
    private String systemName;
    private String code;
    private String sieName;
    private String truName;
    private String mark;
    private Integer sieMaxCourse;
    private Integer truMaxCourse;
    private Timestamp startTime;
    private Timestamp endTime;
    private String startTimeFormat;
    private String endTimeFormat;
    private Integer area;//项目所在区域
    private Integer enabled;//项目是否有效
    //导出Excel人数统计用
    private Integer sieNumber;
    private Integer truNumber;
    private Integer totalNumber;

//    private ProjectPriceBean[] siePrice;
//    private ProjectPriceBean[] truPrice;

    @ExcelField(title="项目ID", align=2, sort=1)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ExcelField(title="SIE项目名称", align=2, sort=2)
    public String getSieName() {
        return sieName;
    }

    public void setSieName(String sieName) {
        this.sieName = sieName;
    }

    @ExcelField(title="TRU项目名称", align=2, sort=3)
    public String getTruName() {
        return truName;
    }

    public void setTruName(String truName) {
        this.truName = truName;
    }

    @ExcelField(title="开始时间", align=2, sort=4)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @ExcelField(title="结束时间", align=2, sort=5)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @ExcelField(title="SIE人数", align=2, sort=6)
    public Integer getSieNumber() {
        return sieNumber;
    }

    public void setSieNumber(Integer sieNumber) {
        this.sieNumber = sieNumber;
    }

    @ExcelField(title="TRU人数", align=2, sort=7)
    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    @ExcelField(title="总人数", align=2, sort=8)
    public Integer getTruNumber() {
        return truNumber;
    }

    public void setTruNumber(Integer truNumber) {
        this.truNumber = truNumber;
    }

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


//    public ProjectPriceBean[] getSiePrice() {
//        return siePrice;
//    }
//
//    public void setSiePrice(ProjectPriceBean[] siePrice) {
//        this.siePrice = siePrice;
//    }
//
//    public ProjectPriceBean[] getTruPrice() {
//        return truPrice;
//    }
//
//    public void setTruPrice(ProjectPriceBean[] truPrice) {
//        this.truPrice = truPrice;
//    }

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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
