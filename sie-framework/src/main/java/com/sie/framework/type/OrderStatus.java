package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum  OrderStatus {

    /** ---------------- 电商 --------------------- */

    /**
     * 提交订单
     */
    SUBMIT(1),


    /**
     * 已完成
     */
    COMPLETE(2),

    /**
     * 取消
     */
    CANCEL(5);




    public static OrderStatus valueOf(int i) {
        for (OrderStatus value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    private int value;

    OrderStatus(int value) {
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

            case SUBMIT:
                return "已提交";

            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
