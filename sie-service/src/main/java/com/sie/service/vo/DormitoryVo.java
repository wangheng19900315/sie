package com.sie.service.vo;

import com.sie.service.bean.BaseBean;
import com.sie.util.annotation.ExcelField;

/**
 * Created by x on 2017/8/12.
 */
public class DormitoryVo{
    private Integer id;
    private String name;
    private boolean readonly;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }
}
