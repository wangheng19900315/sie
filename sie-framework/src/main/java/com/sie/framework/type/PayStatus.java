package com.sie.framework.type;

/**
 * Created by wangheng on 2017/8/16.
 */
public enum PayStatus {

    /** ---------------- 电商 --------------------- */

    /**
     * 提交
     */
    SUBMIT(1),


    /**
     * 支付成功
     */
    COMPLETE(2),


    /**
     * 支付失败
     */
    FAILED(3),

    /**
     * 取消
     */
    CANCEL(5);




    public static PayStatus valueOf(int i) {
        for (PayStatus value : values()) {
            if (value.equals(i)) {
                return value;
            }
        }
        return null;
    }

    private int value;

    PayStatus(int value) {
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
                return "支付成功";
            case FAILED:
                return "支付失败";
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
