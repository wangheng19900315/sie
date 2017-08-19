package com.sie.service.bean;

/**
 * Created by x on 2017/8/19.
 */
public class CoursePrice {
    private Integer system;
    private Integer courseNumber;
    private Double rmbPrice;
    private Double dollarPrice;
    private Double canadianPrice;

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Double getRmbPrice() {
        return rmbPrice;
    }

    public void setRmbPrice(Double rmbPrice) {
        this.rmbPrice = rmbPrice;
    }

    public Double getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(Double dollarPrice) {
        this.dollarPrice = dollarPrice;
    }

    public Double getCanadianPrice() {
        return canadianPrice;
    }

    public void setCanadianPrice(Double canadianPrice) {
        this.canadianPrice = canadianPrice;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }
}
