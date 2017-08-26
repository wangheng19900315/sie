package com.sie.framework.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_project_info")
public class ProjectEntity extends BaseEntity {
//    private Integer id;
    private Integer system;
    private String code;
    private String sieName;
    private String truName;
    private String mark;
//    private Integer sieMaxStudent;
    private Integer sieMaxCourse;
//    private Integer truMaxStudent;
    private Integer truMaxCourse;
    private Timestamp startTime;
    private Timestamp endTime;

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
    @Column(name = "system")
    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    @Basic
    @Column(name = "sie_name")
    public String getSieName() {
        return sieName;
    }

    public void setSieName(String sieName) {
        this.sieName = sieName;
    }

    @Basic
    @Column(name = "tru_name")
    public String getTruName() {
        return truName;
    }

    public void setTruName(String truName) {
        this.truName = truName;
    }


    @Basic
    @Column(name = "mark")
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

//    @Basic
//    @Column(name = "sie_max_student")
//    public Integer getSieMaxStudent() {
//        return sieMaxStudent;
//    }
//
//    public void setSieMaxStudent(Integer sieMaxStudent) {
//        this.sieMaxStudent = sieMaxStudent;
//    }

    @Basic
    @Column(name = "sie_max_course")
    public Integer getSieMaxCourse() {
        return sieMaxCourse;
    }

    public void setSieMaxCourse(Integer sieMaxCourse) {
        this.sieMaxCourse = sieMaxCourse;
    }

//    @Basic
//    @Column(name = "tru_max_student")
//    public Integer getTruMaxStudent() {
//        return truMaxStudent;
//    }
//
//    public void setTruMaxStudent(Integer truMaxStudent) {
//        this.truMaxStudent = truMaxStudent;
//    }

    @Basic
    @Column(name = "tru_max_course")
    public Integer getTruMaxCourse() {
        return truMaxCourse;
    }

    public void setTruMaxCourse(Integer truMaxCourse) {
        this.truMaxCourse = truMaxCourse;
    }


    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }


    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "code")

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
