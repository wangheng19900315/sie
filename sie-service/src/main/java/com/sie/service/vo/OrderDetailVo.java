package com.sie.service.vo;

import com.sie.service.bean.BaseBean;

/**
 * Created by x on 2017/8/12.
 */
public class OrderDetailVo  {
    private Integer id;
    private Integer projectId;
    private String projectName;
    private String   courseIds;
    private String custerNames;
    private Integer courseCount;
    private Integer dormitoryId;
    private String dormitoryName;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }

    public String getCusterNames() {
        return custerNames;
    }

    public void setCusterNames(String custerNames) {
        this.custerNames = custerNames;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
