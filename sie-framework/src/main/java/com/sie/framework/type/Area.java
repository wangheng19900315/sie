package com.sie.framework.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum Area {

    /** ---------------- 学校地区 --------------------- */

    /**
     * 北京
     */
    BJ(1),


    /**
     * 上海
     */
    SH(2);


    public static Area valueOf(int i) {
        for (Area value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    private int value;

    Area(int value) {
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

    public static Map<Integer,String> getAll(){
        Map<Integer,String> areas = new HashMap<>();
        for (Area value : values()) {
            areas.put(value.value,value.getName());
        }
        return areas;
    }

}
