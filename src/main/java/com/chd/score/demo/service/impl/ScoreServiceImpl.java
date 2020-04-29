package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdDirect;
import com.chd.score.demo.bean.ChdLesson;
import com.chd.score.demo.bean.ChdScore;
import com.chd.score.demo.bean.ChdStudent;
import com.chd.score.demo.mapper.ScoreMapper;
import com.chd.score.demo.mapper.StudentMapper;
import com.chd.score.demo.service.ScoreService;
import com.chd.score.demo.service.StudentService;
import com.chd.score.demo.util.PasswordGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentMapper studentMapper;

    @Override
    public PageInfo<ChdScore> chdScoreSelect(int page, int pageSize, ChdScore chdScore) {
        PageInfo<ChdScore> mainPageInfo = PageHelper.startPage(page, pageSize)
                .doSelectPageInfo(() ->  this.scoreMapper.select(chdScore));
        return mainPageInfo;
    }

    @Override
    public ChdScore chdScoreInsert(ChdScore chdScore) {
        PasswordGenerator passwordGenerator = new PasswordGenerator(8,3);
        Calendar cal = Calendar.getInstance();
        String create = cal.getTime()+passwordGenerator.generateRandomPassword();
        chdScore.setIsCreate(create);
        if (StringUtils.isBlank(chdScore.getStudentClassId())){
            ChdStudent chdStudent = new ChdStudent();
            chdStudent.setStudentSchoolId(chdScore.getStudentSchoolId());
            chdStudent = studentMapper.selectOne(chdStudent);
            chdScore.setStudentClassId(chdStudent.getStudentClassId());
        }
        if (StringUtils.isBlank(chdScore.getStudentSchoolId())){
            ChdStudent chdStudent = new ChdStudent();
            chdStudent.setStudentClassId(chdScore.getStudentClassId());
            chdStudent = studentMapper.selectOne(chdStudent);
            String studentSchoolId = chdStudent.getStudentSchoolId();
            chdScore.setStudentSchoolId(studentSchoolId);
        }
        if (StringUtils.isBlank(chdScore.getClassId())){
            ChdStudent chdStudent = new ChdStudent();
            chdStudent.setStudentSchoolId(chdScore.getStudentSchoolId());
            chdStudent = studentMapper.selectOne(chdStudent);
            chdScore.setClassId(chdStudent.getStudentClass());
        }
        int i = scoreMapper.insertSelective(chdScore);
        return chdScore;
    }

    @Override
    public int chdScoreUpdate(String[] keys, ChdScore chdScore) {
        int i = 0;
        for (String key : keys) {
            chdScore.setLessonId(key);
            //TODO 记得修改类
            Example e = new Example(ChdScore.class);
            e.createCriteria().andEqualTo("isCreate",key);
            //TODO 记得修改mapper的类型
            i += scoreMapper.updateByExampleSelective(chdScore, e);
        }
        return i;
    }

    @Override
    public int chdScoreDelete(String[] keys) {
        int i = 0;
        for (String key : keys) {
            i += scoreMapper.deleteByPrimaryKey(key);
        }
        return i;
    }

}
