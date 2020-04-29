package com.chd.score.demo.webbean;

import java.util.List;

public class RecordScore {


    private List<String> lessonId;
    private List<String> notype;
    private List<String> studentId;
    private List<String> score;

    public List<String> getLessonId() {
        return lessonId;
    }

    public void setLessonId(List<String> lessonId) {
        this.lessonId = lessonId;
    }

    public List<String> getNotype() {
        return notype;
    }

    public void setNotype(List<String> notype) {
        this.notype = notype;
    }

    public List<String> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<String> studentId) {
        this.studentId = studentId;
    }

    public List<String> getScore() {
        return score;
    }

    public void setScore(List<String> score) {
        this.score = score;
    }
}
