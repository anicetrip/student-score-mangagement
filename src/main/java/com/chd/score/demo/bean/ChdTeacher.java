package com.chd.score.demo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ChdTeacher implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String teacherId;
    @Column
    private String teacherPsswd;
    @Column
    private String teacherName;
    @Column
    private String teacherNickname;
    @Column
    private String teacherRight;
    @Column
    private String teacherNo;
    @Column
    private String teacherOthers;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherPsswd() {
        return teacherPsswd;
    }

    public void setTeacherPsswd(String teacherPsswd) {
        this.teacherPsswd = teacherPsswd;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherNickname() {
        return teacherNickname;
    }

    public void setTeacherNickname(String teacherNickname) {
        this.teacherNickname = teacherNickname;
    }

    public String getTeacherRight() {
        return teacherRight;
    }

    public void setTeacherRight(String teacherRight) {
        this.teacherRight = teacherRight;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getTeacherOthers() {
        return teacherOthers;
    }

    public void setTeacherOthers(String teacherOthers) {
        this.teacherOthers = teacherOthers;
    }
}
