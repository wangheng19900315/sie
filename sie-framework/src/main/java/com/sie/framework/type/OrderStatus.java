package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum  OrderStatus {


    /**
     * 已订单
     */
    SUBMIT(1),


    /**
     * 已完成
     */
    COMPLETE(2),


    /**
     * 已退款
     */
    REFUND(3),

    /**
     * 申请退款
     */
    APPLY(4),

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
    public static OrderStatus valueOfName(String value) {
        value = value.trim();
        switch (value) {

            case "未支付":
                return SUBMIT;
            case "已完成":
                return COMPLETE;
            case "已退款":
                return REFUND;
            case "退款中":
                return APPLY;
            case "已取消":
                return CANCEL;

            default:
                return null;
        }
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
                return "未支付";
            case COMPLETE:
                return "已完成";
            case REFUND:
                return "已退款";
            case APPLY:
                return "退款中";
            case CANCEL:
                return "已取消";

            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
