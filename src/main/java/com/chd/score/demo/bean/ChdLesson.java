package com.chd.score.demo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ChdLesson implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String lessonId;
    @Column
    private String lessonName;
    @Column
    private String lessonGpa;
    @Column
    private String lessonCollegeId;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonGpa() {
        return lessonGpa;
    }

    public void setLessonGpa(String lessonGpa) {
        this.lessonGpa = lessonGpa;
    }

    public String getLessonCollegeId() {
        return lessonCollegeId;
    }

    public void setLessonCollegeId(String lessonCollegeId) {
        this.lessonCollegeId = lessonCollegeId;
    }
}
