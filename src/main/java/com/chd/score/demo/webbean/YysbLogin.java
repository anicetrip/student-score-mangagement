package com.chd.score.demo.webbean;

import java.io.Serializable;
import java.util.List;

public class YysbLogin implements Serializable {
    /**
     * lessonId : ["60","61"]
     * lessonName : ["数学","英语"]
     * success : true
     * teacherId : 12
     * teacherName : 十二号
     */

    private boolean success;
    private String teacherId;
    private String teacherName;
    private List<String> lessonId;
    private List<String> lessonName;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<String> getLessonId() {
        return lessonId;
    }

    public void setLessonId(List<String> lessonId) {
        this.lessonId = lessonId;
    }

    public List<String> getLessonName() {
        return lessonName;
    }

    public void setLessonName(List<String> lessonName) {
        this.lessonName = lessonName;
    }
//    private boolean success;
//    private String teacherId;
//    private String teacherName;
//    private List<ChdLesson> lessons;
//
//    public String getTeacherId() {
//        return teacherId;
//    }
//
//    public void setTeacherId(String teacherId) {
//        this.teacherId = teacherId;
//    }
//
//    public String getTeacherName() {
//        return teacherName;
//    }
//
//    public void setTeacherName(String teacherName) {
//        this.teacherName = teacherName;
//    }
//
//    public List<ChdLesson> getLessons() {
//        return lessons;
//    }
//
//    public void setLessons(List<ChdLesson> lessons) {
//        this.lessons = lessons;
//    }

//    {"lessonId":["60","61"],"lessonName":["数学","英语"],"success":true,"teacherId":"12","teacherName":"十二号"}



}


