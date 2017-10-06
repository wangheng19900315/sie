package com.sie.service.bean;

import com.sie.framework.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class RegistrationProjectBean extends BaseBean {
    private Integer projectOneId;
    private Integer projectTwoId;
    private String projectsName;
    private boolean checked;

    public Integer getProjectOneId() {
        return projectOneId;
    }

    public void setProjectOneId(Integer projectOneId) {
        this.projectOneId = projectOneId;
    }

    public Integer getProjectTwoId() {
        return projectTwoId;
    }

    public void setProjectTwoId(Integer projectTwoId) {
        this.projectTwoId = projectTwoId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getProjectsName() {
        return projectsName;
    }

    public void setProjectsName(String projectsName) {
        this.projectsName = projectsName;
    }
}
