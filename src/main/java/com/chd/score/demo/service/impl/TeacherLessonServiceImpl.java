package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdStudent;
import com.chd.score.demo.bean.TeacherLesson;
import com.chd.score.demo.mapper.TeacherLessonMapper;
import com.chd.score.demo.service.TeacherLessonService;
import com.chd.score.demo.util.PasswordGenerator;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;

@Service
public class TeacherLessonServiceImpl  implements TeacherLessonService {
    @Autowired
    TeacherLessonMapper teacherLessonMapper;


    @Override
    public PageInfo<TeacherLesson> teacherLessonSelect(int page, int pageSize, TeacherLesson teacherLesson) {
        PageInfo<TeacherLesson> mainPageInfo = PageHelper.startPage(page, pageSize)
                .doSelectPageInfo(() ->  this.teacherLessonMapper.select(teacherLesson));
        return mainPageInfo;
    }

    @Override
    public TeacherLesson teacherLessonInsert(TeacherLesson teacherLesson) {
        PasswordGenerator passwordGenerator = new PasswordGenerator(8,3);
        Calendar cal = Calendar.getInstance();
        String create = cal.getTime()+passwordGenerator.generateRandomPassword();
        //TODO 修改课程编号
        teacherLesson.setIsCreate(cal.DATE+""+passwordGenerator);
        int i = teacherLessonMapper.insertSelective(teacherLesson);
        return teacherLesson;
    }

    @Override
    public int chdStudentUpdate(String[] keys, TeacherLesson teacherLesson) {
        int i = 0;
        for (String key : keys) {
            teacherLesson.setIsCreate(key);
            //TODO 记得修改类
            Example e = new Example(TeacherLesson.class);
            e.createCriteria().andEqualTo("isCreate",key);
            //TODO 记得修改mapper的类型
            i += teacherLessonMapper.updateByExampleSelective(teacherLesson, e);
        }
        return i;
    }

    @Override
    public int teacherLessonDelete(String[] keys) {
        int i = 0;
        for (String key : keys) {
            i += teacherLessonMapper.deleteByPrimaryKey(key);
        }
        return i;
    }
}
