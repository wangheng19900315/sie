package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_order_info")
public class OrderEntity extends BaseEntity {
    private String code;
    private Double money;
    private Double discount; //管理员优惠金额
    private Double crDiscount; //cr优惠金额
    private Double payMoney;
    private Integer payType;//支付方式
    private Double couponDiscount; //优惠卷优惠金额
    private Integer orderType;
    private Integer status;
    private Timestamp payTime;
    private String remark;
    private CrEntity crEntity;
    private CouponEntity couponEntity;
    private Integer systemType;
    private StudentEntity studentEntity;
    private Timestamp orderTime;
    //订单退款信息
    private Double refundMoney;
    private String refundReason;
    private Integer refundType;
    private String payee;//收款人姓名
    //银行转账信息
    private String depositBank;//开户行
    private String account;//账号

    //支付宝账号
    private String alipay;

    //加币邮件
    private String refundEmail;

    private List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();


    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "money")
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "pay_time")
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderEntity", orphanRemoval = true)
    @Where(clause = "h_delete=0")
    public List<OrderDetailEntity> getOrderDetailEntityList() {
        return orderDetailEntityList;
    }

    public void setOrderDetailEntityList(List<OrderDetailEntity> orderDetailEntityList) {
        this.orderDetailEntityList = orderDetailEntityList;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "cr_id", nullable = true, columnDefinition = "COMMENT 'cr id'")
    @Where(clause = "h_delete=0")
    public CrEntity getCrEntity() {
        return crEntity;
    }

    public void setCrEntity(CrEntity crEntity) {
        this.crEntity = crEntity;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = true, columnDefinition = "COMMENT '优惠卷id'")
    @Where(clause = "h_delete=0")
    public CouponEntity getCouponEntity() {
        return couponEntity;
    }

    public void setCouponEntity(CouponEntity couponEntity) {
        this.couponEntity = couponEntity;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = true, columnDefinition = "COMMENT '学生id'")
    @Where(clause = "h_delete=0")
    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    @Column(name = "discount")
    public Double getDiscount() {
        return discount;
    }


    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Column(name = "cr_discount")
    public Double getCrDiscount() {
        return crDiscount;
    }

    public void setCrDiscount(Double crDiscount) {
        this.crDiscount = crDiscount;
    }

    @Column(name = "coupon_discount")
    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    @Column(name = "order_type")
    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    @Column(name = "system_type")
    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    @Column(name = "pay_money")
    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
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
    @Column(name = "order_time")
    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "refund_money")

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    @Basic
    @Column(name = "refund_reason")
    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    @Basic
    @Column(name = "refund_type")
    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    @Basic
    @Column(name = "payee")
    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    @Basic
    @Column(name = "deposit_bank")
    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "alipay")
    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    @Basic
    @Column(name = "refund_email")
    public String getRefundEmail() {
        return refundEmail;
    }

    public void setRefundEmail(String refundEmail) {
        this.refundEmail = refundEmail;
    }
}
