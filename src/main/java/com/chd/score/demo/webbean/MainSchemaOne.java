package com.chd.score.demo.webbean;

import com.chd.score.demo.bean.ChdMain;

import java.io.Serializable;

public class MainSchemaOne implements Serializable {
    private int code=0;
    private String message;
    private boolean success=true;
    private String  total;
    private ChdMain data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ChdMain getData() {
        return data;
    }

    public void setData(ChdMain data) {
        this.data = data;
    }

}
