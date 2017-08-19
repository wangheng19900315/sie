package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum OrderType {

    /** ---------------- 电商 --------------------- */

    /**
     * 正常订单
     */
    SUBMIT(1),


    /**
     * 管理员申请
     */
    COMPLETE(2),


    /**
     * 已退款
     */
    REFUND(3),

    /**
     * 取消
     */
    CANCEL(5);




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

            case SUBMIT:
                return "已提交";
            case COMPLETE:
                return "已完成";
            case REFUND:
                return "已退款";
            case CANCEL:
                return "取消";

            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
