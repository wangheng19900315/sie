package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum RefundType {

    /** ---------------- 退款方式 --------------------- */

    /**
     * 银行转账
     */
    BANK(1),
    /**
     * 支付宝
     */
    AIPAY(2),
    /**
     * 微信支付
     */
    WECHAT(3),
    /**
     * 加币
     */
    CANADIANDOLLARS(4);






    public static RefundType valueOf(int i) {
        for (RefundType value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    private int value;

    RefundType(int value) {
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
            case BANK:
                return "银行转账";
            case CANADIANDOLLARS:
                return "加币";

            default:
                return "未定义";
        }
    }

    public int value() {
        return this.value;
    }

}
