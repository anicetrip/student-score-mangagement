package com.chd.score.demo.controller;

import com.chd.score.demo.bean.ArrWebStruct;
import com.chd.score.demo.bean.ChdScore;
import com.chd.score.demo.bean.ChdStudent;
import com.chd.score.demo.mapper.StudentMapper;
import com.chd.score.demo.service.ScoreService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.RecordScore;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/recorder")
public class RecordController {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ScoreService scoreService;


    @RequestMapping("score")
    @ResponseBody
    public List<String > recorderScore(@Param("lessonId") String[]  lessonId, @Param("notype") String[]  notype, @Param("studentId") String[]  studentId, @Param("score") String[]  score,@Param("teacherId") String[]  teacherId){
        int len = lessonId.length;
        ArrayList<String> indexes = new ArrayList<>();
//        indexes.add("2");
        for (int i = 0;i<len;i++){
            ChdStudent chdStudent = new ChdStudent();
            String str = "class";
            String num = notype[i].substring(0,notype[i].length()-2);
            if (num.equals(str)){
                chdStudent.setStudentClassId(studentId[i]);
                chdStudent = studentMapper.selectOne(chdStudent);
            }else {
                chdStudent.setStudentSchoolId(studentId[i]);
                chdStudent = studentMapper.selectOne(chdStudent);
            }
            if (chdStudent == null){
                indexes.add(i+"");
            }
        }
        if (indexes.isEmpty()){
            for (int i = 0;i<len;i++){
                ChdScore chdScore = new ChdScore();
                if (notype[i].equals("classNo") ){
                    chdScore.setStudentClassId(studentId[i]);
                }else {
                    chdScore.setStudentSchoolId(studentId[i]);
                }
                chdScore.setScore(score[i]);
                chdScore.setTeacherId(teacherId[i]);
                chdScore.setLessonId(lessonId[i]);


                scoreService.chdScoreInsert(chdScore);
            }

        }


//
////        System.out.println(json);
//        ArrayList<String> indexes = new ArrayList<>();
////        indexes.add("0");
////        indexes.add("1");
//        indexes.add("2");
////        indexes.add("3");
////        indexes.add("4");
//        return indexes;
    return indexes;

    }
}

