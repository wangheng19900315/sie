package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_registration_project_info")
public class RegistrationProjectEntity extends BaseEntity {
//    private Integer id;
//    private Integer projectNumber;//只能为1或者2
//    private ProjectEntity projectEntityOne;
//    private ProjectEntity projectEntityTwo;
//
//    @Basic
//    @Column(name = "project_number")
//
//    public Integer getProjectNumber() {
//        return projectNumber;
//    }
//
//    public void setProjectNumber(Integer projectNumber) {
//        this.projectNumber = projectNumber;
//    }
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_one_id", nullable = true, columnDefinition = "COMMENT 'project id'")
//    @Where(clause = "h_delete=0")
//    public ProjectEntity getProjectEntityOne() {
//        return projectEntityOne;
//    }
//
//    public void setProjectEntityOne(ProjectEntity projectEntityOne) {
//        this.projectEntityOne = projectEntityOne;
//    }
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_two_id", nullable = true, columnDefinition = "COMMENT 'project id'")
//    @Where(clause = "h_delete=0")
//    public ProjectEntity getProjectEntityTwo() {
//        return projectEntityTwo;
//    }
//
//    public void setProjectEntityTwo(ProjectEntity projectEntityTwo) {
//        this.projectEntityTwo = projectEntityTwo;
//    }
}
