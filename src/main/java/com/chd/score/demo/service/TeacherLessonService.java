package com.chd.score.demo.service;

import com.chd.score.demo.bean.TeacherLesson;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageInfo;

public interface TeacherLessonService {
//    Schema teacherLessonSchema();

    PageInfo<TeacherLesson> teacherLessonSelect(int page, int pageSize, TeacherLesson teacherLesson);

    TeacherLesson teacherLessonInsert(TeacherLesson teacherLesson);

    int chdStudentUpdate(String[] keys, TeacherLesson teacherLesson);

    int teacherLessonDelete(String[] keys);
}
