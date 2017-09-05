package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum PayType {

    /** ---------------- 电商 --------------------- */

    /**
     * 微信支付
     */
    WECHAT(1),


    /**
     * 支付宝
     */
    AIPAY(2),


    /**
     * 银联支付
     */
    UNION(3),

    /**
     * 人工支付
     */
    MANUAL(4);




    public static PayType valueOf(int i) {
        for (PayType value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    public static PayType valueOfName(String value) {
        value = value.trim();
        switch (value) {

            case "微信":
                return WECHAT;
            case "支付宝":
                return AIPAY;
            case "银联":
                return UNION;
            case "人工":
                return MANUAL;

            default:
                return null;
        }
    }

    private int value;

    PayType(int value) {
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

            case WECHAT:
                return "微信";
            case AIPAY:
                return "支付宝";
            case UNION:
                return "银联";
            case MANUAL:
                return "人工";

            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
