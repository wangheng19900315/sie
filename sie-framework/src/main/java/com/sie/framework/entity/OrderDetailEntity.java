package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_order_detail_info")
public class OrderDetailEntity extends BaseEntity{
    private String   courseIds;
    private Integer courseCount;
//    private Double total;
    private OrderEntity orderEntity;
    private ProjectEntity projectEntity;
    private DormitoryEntity dormitoryEntity;


    @Basic
    @Column(name = "course_ids")
    public String getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }

    @Basic
    @Column(name = "course_count")
    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
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

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = true, columnDefinition = "COMMENT '项目id'")
    @Where(clause = "h_delete=0")
    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "dormitory_id", nullable = true, columnDefinition = "COMMENT '宿舍id'")
    @Where(clause = "h_delete=0")
    public DormitoryEntity getDormitoryEntity() {
        return dormitoryEntity;
    }

    public void setDormitoryEntity(DormitoryEntity dormitoryEntity) {
        this.dormitoryEntity = dormitoryEntity;
    }



}
