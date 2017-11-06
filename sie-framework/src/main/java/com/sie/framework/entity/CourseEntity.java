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
    private String courseID;
//    private String chineseName;
//    private String englishName;
    private Integer professorId;
    private String professorName;// 教授名称
    private String startTime;
    private String endTime;
    private ProjectEntity projectEntity;
//    private Integer projectId;
//    private Integer isSie;
    private String sieCode;
    private String sieChineseName;
    private String sieEnglishName;
    private Integer maxStudent;
    private Integer sieTotalNumber;
//    private Integer isTru;
    private String truCode;
    private String truChineseName;
    private String truEnglishName;
//    private Integer truMaxStudent;
    private Integer truTotalNumber;

    private Integer school;//校区
    private String classroom;//上课地点

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
    @Column(name = "course_ID")
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

//    @Basic
//    @Column(name = "chinese_name")
//    public String getChineseName() {
//        return chineseName;
//    }
//
//    public void setChineseName(String chineseName) {
//        this.chineseName = chineseName;
//    }
//
//    @Basic
//    @Column(name = "english_name")
//    public String getEnglishName() {
//        return englishName;
//    }
//
//    public void setEnglishName(String englishName) {
//        this.englishName = englishName;
//    }

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
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = true, columnDefinition = "COMMENT '课程'")
    @Where(clause = "h_delete=0")
    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    @Basic
    @Column(name = "project_name")
    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    @Basic
    @Column(name = "sie_code")
    public String getSieCode() {
        return sieCode;
    }

    public void setSieCode(String sieCode) {
        this.sieCode = sieCode;
    }

    @Basic
    @Column(name = "sie_chinese_name")
    public String getSieChineseName() {
        return sieChineseName;
    }

    public void setSieChineseName(String sieChineseName) {
        this.sieChineseName = sieChineseName;
    }

    @Basic
    @Column(name = "sie_english_name")
    public String getSieEnglishName() {
        return sieEnglishName;
    }

    public void setSieEnglishName(String sieEnglishName) {
        this.sieEnglishName = sieEnglishName;
    }

    @Basic
    @Column(name = "tru_chinese_name")
    public String getTruChineseName() {
        return truChineseName;
    }

    public void setTruChineseName(String truChineseName) {
        this.truChineseName = truChineseName;
    }

    @Basic
    @Column(name = "tru_english_name")
    public String getTruEnglishName() {
        return truEnglishName;
    }

    public void setTruEnglishName(String truEnglishName) {
        this.truEnglishName = truEnglishName;
    }

    @Basic
    @Column(name = "max_student")
    public Integer getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(Integer maxStudent) {
        this.maxStudent = maxStudent;
    }

    @Basic
    @Column(name = "sie_total_number")
    public Integer getSieTotalNumber() {
        return sieTotalNumber;
    }

    public void setSieTotalNumber(Integer sieTotalNumber) {
        this.sieTotalNumber = sieTotalNumber;
    }

    @Basic
    @Column(name = "tru_code")
    public String getTruCode() {
        return truCode;
    }

    public void setTruCode(String truCode) {
        this.truCode = truCode;
    }

    @Basic
    @Column(name = "tru_total_number")
    public Integer getTruTotalNumber() {
        return truTotalNumber;
    }

    public void setTruTotalNumber(Integer truTotalNumber) {
        this.truTotalNumber = truTotalNumber;
    }


    @Basic
    @Column(name = "school")
    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }


    @Basic
    @Column(name = "classroom")
    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
