package com.sie.service.vo;

import java.util.List;

/**
 * Created by x on 2017/10/31.
 */
public class CourseVoMap {
    private String key;
    List<CourseVo> courseVos;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<CourseVo> getCourseVos() {
        return courseVos;
    }

    public void setCourseVos(List<CourseVo> courseVos) {
        this.courseVos = courseVos;
    }
}
