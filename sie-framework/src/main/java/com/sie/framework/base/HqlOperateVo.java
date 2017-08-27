package com.sie.framework.base;

/**
 * Created by wangheng on 2017/8/27.
 */
public class HqlOperateVo {

    private  String name;
    private String operate;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HqlOperateVo{" +
                "name='" + name + '\'' +
                ", operate='" + operate + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
