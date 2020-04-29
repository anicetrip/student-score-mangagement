package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdLesson;
import com.chd.score.demo.bean.ChdTeacher;
import com.chd.score.demo.bean.TeacherLesson;
import com.chd.score.demo.mapper.LessonMapper;
import com.chd.score.demo.mapper.TeacherLessonMapper;
import com.chd.score.demo.mapper.TeacherMapper;
import com.chd.score.demo.service.SpeechService;
import com.chd.score.demo.webbean.YysbLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SpeechServiceImpl implements SpeechService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    LessonMapper lessonMapper;
    @Autowired
    TeacherLessonMapper teacherLessonMapper;


    @Override
    public YysbLogin login(String userID, String password) {
        YysbLogin yysbLogin = new YysbLogin();
        ChdTeacher chdTeacher = new ChdTeacher();
        chdTeacher.setTeacherPsswd(password);
        chdTeacher.setTeacherId(userID);
        chdTeacher = teacherMapper.selectOne(chdTeacher);
        if (chdTeacher == null){
            yysbLogin.setSuccess(false);
            return yysbLogin;
        }
        TeacherLesson teacherLesson = new TeacherLesson();
        String teacherId = chdTeacher.getTeacherId();
        teacherLesson.setTeacherId(teacherId);
        List<TeacherLesson> select = teacherLessonMapper.select(teacherLesson);
        yysbLogin.setSuccess(true);
        yysbLogin.setTeacherId(teacherId);
        String teacherName = chdTeacher.getTeacherName();
        yysbLogin.setTeacherName(teacherName);
        if (select.size()==0){
            return yysbLogin;
        }
        List<ChdLesson> chdLessons = new ArrayList<>();
        for (TeacherLesson lesson : select) {
            ChdLesson chdLesson = new ChdLesson();
            chdLesson.setLessonId(lesson.getLessonId());
            chdLessons.add(lessonMapper.selectOne(chdLesson));
        }
         yysbLogin = toYysb(yysbLogin, chdLessons);
        return yysbLogin;
    }


    public static YysbLogin toYysb(YysbLogin yysbLogin,List<ChdLesson> chdLessonList){
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        for (ChdLesson chdLesson : chdLessonList) {
            id.add(chdLesson.getLessonId());
            name.add(chdLesson.getLessonName());
        }
        yysbLogin.setLessonId(id);
        yysbLogin.setLessonName(name);
        return yysbLogin;
    }
}