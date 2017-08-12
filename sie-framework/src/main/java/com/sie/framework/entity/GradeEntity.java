package com.sie.framework.entity;

import javax.persistence.*;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "grade")
public class GradeEntity extends BaseEntity {
    private Integer id;
    private Integer grade;
    private Integer orderDetailId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "grade")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "order_detail_id")
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

}
