package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum OrderType {


    /**
     * 用户下单
     */
    USER(1),


    /**
     * 管理员下单
     */
    ADMIN(2);






    public static OrderType valueOf(int i) {
        for (OrderType value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    private int value;

    OrderType(int value) {
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

            case USER:
                return "用户";
            case ADMIN:
                return "管理员";

            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
