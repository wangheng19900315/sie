package com.sie.service.vo;

import java.util.Map;

/**
 * Created by x on 2017/8/12.
 */

public class OrderPriceVo {
    private double coursePrice;
    private double totalPrice;
    private Map<Integer,Double> dormitoryPrice;

    public double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, Double> getDormitoryPrice() {
        return dormitoryPrice;
    }

    public void setDormitoryPrice(Map<Integer, Double> dormitoryPrice) {
        this.dormitoryPrice = dormitoryPrice;
    }
}
