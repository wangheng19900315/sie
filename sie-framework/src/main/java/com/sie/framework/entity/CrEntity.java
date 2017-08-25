package com.sie.framework.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_cr_info")
public class CrEntity extends BaseEntity{
//    private Integer id;
    private String code;
    private String personName;
    private Integer used;
    private Double rmbPrice;
    private Double dollarPrice;
    private Double canadianPrice;

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "person_name")
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
    @Column(name = "used")
    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
}
