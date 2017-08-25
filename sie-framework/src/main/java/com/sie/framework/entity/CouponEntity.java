package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_coupon_info")
public class CouponEntity extends BaseEntity{


//    private Integer id;
    private String name;
    private String code;
//    private Integer status;
    private Double rmbDiscount;
    private Double dollarDiscount;
    private Double canadianDiscount;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer enabled;

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


//    @Column(name = "status")
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }

    @Column(name = "rmb_discount")
    public Double getRmbDiscount() {
        return rmbDiscount;
    }

    public void setRmbDiscount(Double rmbDiscount) {
        this.rmbDiscount = rmbDiscount;
    }

    @Column(name = "dollar_discount")
    public Double getDollarDiscount() {
        return dollarDiscount;
    }

    public void setDollarDiscount(Double dollarDiscount) {
        this.dollarDiscount = dollarDiscount;
    }

    @Column(name = "canadian_discount")
    public Double getCanadianDiscount() {
        return canadianDiscount;
    }

    public void setCanadianDiscount(Double canadianDiscount) {
        this.canadianDiscount = canadianDiscount;
    }



    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Column(name = "enabled")

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
