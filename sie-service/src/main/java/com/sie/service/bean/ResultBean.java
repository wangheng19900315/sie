package com.sie.service.bean;

import java.io.Serializable;

/**
 * Created by wangheng on 2017/8/12.
 */
public class ResultBean<T> implements Serializable{

    private boolean success;

    private String message;

    private Integer id;

    private T data;



    public ResultBean(){
        this.success = false;
        this.message = "操作失败，请联系管理员";
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
