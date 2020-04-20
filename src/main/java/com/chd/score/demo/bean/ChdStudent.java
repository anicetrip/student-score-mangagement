package com.chd.score.demo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ChdStudent implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String studentClassId;
    @Column
    private String studnetSchoolId;
    @Column
    private String studentName;
    @Column
    private String studentStatus;
    @Column
    private String studentOthers;
    @Column
    private String studentClass;

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(String studentClass_id) {
        this.studentClassId = studentClassId;
    }

    public String getStudnetSchoolId() {
        return studnetSchoolId;
    }

    public void setStudnetSchoolId(String studnetSchoolId) {
        this.studnetSchoolId = studnetSchoolId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getStudentOthers() {
        return studentOthers;
    }

    public void setStudentOthers(String studentOthers) {
        this.studentOthers = studentOthers;
    }
}
