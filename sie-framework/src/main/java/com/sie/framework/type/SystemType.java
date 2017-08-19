package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum SystemType {

    /** ---------------- 所属前台系统 --------------------- */

    /**
     * SIE系统
     */
    SIE(1),


    /**
     * TRU系统
     */
    TRU(2),


    /**
     * SIE系统和TRU系统
     */
    SIEANDTRU(3);


    public static SystemType valueOf(int i) {
        for (SystemType value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    private int value;

    SystemType(int value) {
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

            case SIE:
                return "SIE系统";
            case TRU:
                return "TRU系统";
            case SIEANDTRU:
                return "SIE系统&TRU系统";
            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
