package com.chd.score.demo.bean;

import java.io.Serializable;

public class ArrWebStruct implements Serializable {
    private String[] data;
    private String total;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
