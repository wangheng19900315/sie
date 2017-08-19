package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by wangheng on 2017/8/16.
 */
@Entity
@Table(name = "t_order_pay_info")
public class OrderPayEntity extends BaseEntity {

    private Integer payStatus;
    private Integer payType;
    private Double payTotal;
    private String payNumber;
    private String payName;
    private String remark;
    private OrderEntity orderEntity;


    @Basic
    @Column(name = "pay_status")
    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    @Basic
    @Column(name = "pay_type")
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "pay_total")
    public Double getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Double payTotal) {
        this.payTotal = payTotal;
    }

    @Basic
    @Column(name = "pay_number")
    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    @Basic
    @Column(name = "pay_name")
    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = true, columnDefinition = "COMMENT '订单id'")
    @Where(clause = "h_delete=0")
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
