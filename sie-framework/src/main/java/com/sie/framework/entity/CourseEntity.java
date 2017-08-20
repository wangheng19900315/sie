package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_course_info")
public class CourseEntity extends BaseEntity{
//    private Integer id;
    private Integer system;
    private String chineseName;
    private String englishName;
    private Integer professorId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer projectId;
//    private Integer isSie;
    private String sieCode;
    private Integer sieMaxStudent;
    private Integer sieTotalNumber;
//    private Integer isTru;
    private String truCode;
    private Integer truMaxStudent;
    private Integer truTotalNumber;

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
    @Column(name = "chinese_name")
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @Basic
    @Column(name = "english_name")
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Basic
    @Column(name = "professor_id")
    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
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
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

//    @Basic
//    @Column(name = "is_sie")
//    public Integer getIsSie() {
//        return isSie;
//    }
//
//    public void setIsSie(Integer isSie) {
//        this.isSie = isSie;
//    }

    @Basic
    @Column(name = "sie_code")
    public String getSieCode() {
        return sieCode;
    }

    public void setSieCode(String sieCode) {
        this.sieCode = sieCode;
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
    @Column(name = "sie_total_number")
    public Integer getSieTotalNumber() {
        return sieTotalNumber;
    }

    public void setSieTotalNumber(Integer sieTotalNumber) {
        this.sieTotalNumber = sieTotalNumber;
    }

//    @Basic
//    @Column(name = "is_tru")
//    public Integer getIsTru() {
//        return isTru;
//    }
//
//    public void setIsTru(Integer isTru) {
//        this.isTru = isTru;
//    }

    @Basic
    @Column(name = "tru_code")
    public String getTruCode() {
        return truCode;
    }

    public void setTruCode(String truCode) {
        this.truCode = truCode;
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
    @Column(name = "tru_total_number")
    public Integer getTruTotalNumber() {
        return truTotalNumber;
    }

    public void setTruTotalNumber(Integer truTotalNumber) {
        this.truTotalNumber = truTotalNumber;
    }
}
