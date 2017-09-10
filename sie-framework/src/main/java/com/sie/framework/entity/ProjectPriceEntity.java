package com.sie.framework.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_project_price_info")
public class ProjectPriceEntity extends BaseEntity {
    private Integer projectNumber;
    private Integer courseNumber;
    private Double rmbPrice;
    private Double dollarPrice;
    private Double canadianPrice;
    private Integer system;

    @Basic
    @Column(name = "project_number")
    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    @Basic
    @Column(name = "course_number")
    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    @Basic
    @Column(name = "rmb_price")
    public Double getRmbPrice() {
        return rmbPrice;
    }

    public void setRmbPrice(Double rmbPrice) {
        this.rmbPrice = rmbPrice;
    }

    @Basic
    @Column(name = "dollar_price")
    public Double getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(Double dollarPrice) {
        this.dollarPrice = dollarPrice;
    }


    @Basic
    @Column(name = "canadian_price")
    public Double getCanadianPrice() {
        return canadianPrice;
    }

    public void setCanadianPrice(Double canadianPrice) {
        this.canadianPrice = canadianPrice;
    }

    @Basic
    @Column(name = "system")
    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }
}
