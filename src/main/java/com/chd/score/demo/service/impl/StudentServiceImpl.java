package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdClass;
import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.ChdStudent;
import com.chd.score.demo.mapper.StudentMapper;
import com.chd.score.demo.service.StudentService;
import com.chd.score.demo.util.PasswordGenerator;
import com.chd.score.demo.util.Zero;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
@Service
public class StudentServiceImpl  implements StudentService {
    @Autowired
    StudentMapper studentMapper;



    @Override
    public PageInfo<ChdStudent> chdStudentSelect(int page, int pageSize, ChdStudent chdStudent) {
        PageInfo<ChdStudent> mainPageInfo = PageHelper.startPage(page, pageSize)
                .doSelectPageInfo(() ->  this.studentMapper.select(chdStudent));
        return mainPageInfo;
    }

    @Override
    public ChdStudent chdStudentInsert(ChdStudent chdStudent) {
        int i = studentMapper.insert(chdStudent);
        chdStudent = studentMapper.selectOne(chdStudent);
        //设置年级学号
        Calendar cal = Calendar.getInstance();
        String studentSchoolId= cal.get(Calendar.YEAR) + Zero.zeroAddzdy(chdStudent.getId(),5);
        chdStudent.setStudentSchoolId(studentSchoolId);
        //设置班级学号
        ChdStudent temp = new ChdStudent();
        temp.setStudentClass(chdStudent.getStudentClass());
        List<ChdStudent> select = studentMapper.select(temp);
        String classId = select.size() == 1
                ? chdStudent.getStudentClass()+"01"
                :new BigInteger(select.get(select.size()-2).getStudentClassId()).add(new BigInteger("1")).toString();
        chdStudent.setStudentClassId(classId);
        //插入
        Example e = new Example(ChdStudent.class);
        e.createCriteria().andEqualTo("id",chdStudent.getId());
        i += studentMapper.updateByExampleSelective(chdStudent, e);
        return chdStudent;
    }

    @Override
    public int chdClassDelete(String[] keys) {
        int i = 0;
        for (String key : keys) {
            i += studentMapper.deleteByPrimaryKey(key);
        }
        return i;
    }

    @Override
    public int chdStudentUpdate(String[] keys, ChdStudent chdStudent) {
        int i = 0;
        for (String key : keys) {
            chdStudent.setId(key);
            //TODO 记得修改类
            Example e = new Example(ChdStudent.class);
            e.createCriteria().andEqualTo("id",key);
            //TODO 记得修改mapper的类型
            i += studentMapper.updateByExampleSelective(chdStudent, e);
        }
        return i;
    }

}
