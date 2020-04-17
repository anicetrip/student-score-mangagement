package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdTeacher;
import com.chd.score.demo.mapper.TeacherMapper;
import com.chd.score.demo.service.TeacherService;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public String  login(String teacherId, String password) {
        ChdTeacher chdTeacher = new ChdTeacher();
        chdTeacher.setTeacherId(teacherId);
        chdTeacher.setTeacherPsswd(password);
        ChdTeacher teacher = teacherMapper.selectOne(chdTeacher);
        if (teacher != null){
            return teacher.getTeacherName();
        }
        return null;
    }
}
