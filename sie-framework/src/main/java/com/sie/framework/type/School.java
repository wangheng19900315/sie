package com.sie.framework.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum School {

    /** ---------------- 学校地区 --------------------- */

    /**
     * 北京交通大学
     */
    BJUT(1),


    /**
     * 华东师范大学
     */
    ECNU(2),

    /**
     * 南京航空航天大学
     */
    NUAA(3);


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

            case BJUT:
                return "北京交通大学";
            case ECNU:
                return "华东师范大学";
            case NUAA:
                return "南京航空航天大学";
            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

    public static Map<Integer,String> getAll(){
        Map<Integer,String> schools = new HashMap<>();
        for (School value : values()) {
            schools.put(value.value,value.getName());
        }
        return schools;
    }

}
