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
    private Integer projectOneId;
    private Integer projectTwoId;
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

    @Basic
    @Column(name = "project_one_id")
    public Integer getProjectOneId() {
        return projectOneId;
    }

    public void setProjectOneId(Integer projectOneId) {
        this.projectOneId = projectOneId;
    }

    @Basic
    @Column(name = "project_two_id")
    public Integer getProjectTwoId() {
        return projectTwoId;
    }

    public void setProjectTwoId(Integer projectTwoId) {
        this.projectTwoId = projectTwoId;
    }
}
