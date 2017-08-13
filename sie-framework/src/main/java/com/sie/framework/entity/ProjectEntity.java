package com.sie.framework.entity;

import javax.persistence.*;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "project")
public class ProjectEntity extends BaseEntity {
    private Integer id;
    private String name;
    private String mark;
    private Integer sieMaxStudent;
    private Integer sieMaxCourse;
    private Integer truMaxStudent;
    private Integer truMaxCourse;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "mark")
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "sie_max_student")
    public Integer getSieMaxStudent() {
        return sieMaxStudent;
    }

    public void setSieMaxStudent(Integer sieMaxStudent) {
        this.sieMaxStudent = sieMaxStudent;
    }

    @Basic
    @Column(name = "sie_max_course")
    public Integer getSieMaxCourse() {
        return sieMaxCourse;
    }

    public void setSieMaxCourse(Integer sieMaxCourse) {
        this.sieMaxCourse = sieMaxCourse;
    }

    @Basic
    @Column(name = "tru_max_student")
    public Integer getTruMaxStudent() {
        return truMaxStudent;
    }

    public void setTruMaxStudent(Integer truMaxStudent) {
        this.truMaxStudent = truMaxStudent;
    }

    @Basic
    @Column(name = "tru_max_course")
    public Integer getTruMaxCourse() {
        return truMaxCourse;
    }

    public void setTruMaxCourse(Integer truMaxCourse) {
        this.truMaxCourse = truMaxCourse;
    }

}
