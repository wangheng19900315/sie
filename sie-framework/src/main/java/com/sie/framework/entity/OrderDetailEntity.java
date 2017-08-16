package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_order_detail_info")
public class OrderDetailEntity extends BaseEntity{
//    private Integer id;
    private Integer projectId;
    private Integer courseId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = true, columnDefinition = "COMMENT '订单id'")
    @Where(clause = "h_delete=0")
    private OrderEntity orderEntity;

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
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

}
