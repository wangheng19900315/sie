package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum School {

    /** ---------------- 学校地区 --------------------- */

    /**
     * 北京
     */
    BJ(1),


    /**
     * 上海
     */
    SH(2);


    public static School valueOf(int i) {
        for (School value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    private int value;

    School(int value) {
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

            case BJ:
                return "北京校区";
            case SH:
                return "上海校区";
            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
