package com.chd.score.demo.webbean;

import com.chd.score.demo.bean.ChdCollege;

import java.util.List;

public class CollegeSchema {
    /**
     * code : 0
     * data : [{"collegeId":"10","collegeName":"控制"},{"collegeId":"11`","collegeName":"土木"}]
     * message :
     * success : true
     * total : 31461
     */

    private int code=0;
    private String message;
    private boolean success=false;
    private int total=1;
    private List<ChdCollege> data;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ChdCollege> getData() {
        return data;
    }

    public void setData(List<ChdCollege> data) {
        this.data = data;
    }

//    {
//        "code":0,
//        "data":
//        [{
//            "collegeId":"10",
//            "collegeName":"控制",
//    },{
//        "collegeId":"11`",
//        "collegeName":"土木",
//    }
//
//        ],
//    "message":"",
//            "success":true,
//            "total":31461
//    }


}
