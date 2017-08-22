package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_grade_info")
public class GradeEntity extends BaseEntity {
//    private Integer id;
    private Double grade;
    private ProjectEntity projectEntity;
    private CourseEntity courseEntity;
    private StudentEntity studentEntity;
    private Integer systemType;


    @Basic
    @Column(name = "grade")
    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Column(name = "system_type")
    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
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
    @JoinColumn(name = "course_id", nullable = true, columnDefinition = "COMMENT '课程'")
    @Where(clause = "h_delete=0")
    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = true, columnDefinition = "COMMENT '课程'")
    @Where(clause = "h_delete=0")
    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }
}
