package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum OrderDetailStatus {


    /**
     * 正常
     */
    NORMAL(1),


    /**
     * 已退项目
     */
    RETREATPROJECT(2),


    /**
     * 已退课程
     */
    RETREATPCOURSE(3);




    public static OrderDetailStatus valueOf(int i) {
        for (OrderDetailStatus value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    private int value;

    OrderDetailStatus(int value) {
        this.value = value;
    }

    public boolean equals(int value) {
        return this.value == value;
    }

    public boolean equals(Integer value) {
        if (value != null) {
            return this.value == value.intValue();
        } else {
            return false;
        }
    }

    public String getName() {
        switch (this) {

            case NORMAL:
                return "正常";
            case RETREATPROJECT:
                return "已退项目";
            case RETREATPCOURSE:
                return "已退课程";
            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
