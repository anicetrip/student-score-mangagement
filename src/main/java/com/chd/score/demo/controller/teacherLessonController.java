package com.chd.score.demo.controller;

import com.chd.score.demo.bean.ChdMain;
import com.chd.score.demo.bean.ChdStudent;
import com.chd.score.demo.bean.TeacherLesson;
import com.chd.score.demo.bean.WebStruct;
import com.chd.score.demo.service.TeacherLessonService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/api/chdTeacher")
public class teacherLessonController {
    @Autowired
    TeacherLessonService teacherLessonService;




    @RequestMapping("select")
    @ResponseBody
    public String  teacherLessonSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        TeacherLesson teacherLesson = new TeacherLesson();
        teacherLesson.setLessonId((String) map.get("lessonId"));
        teacherLesson.setTeacherId((String) map.get("teacherId"));
        //处理传参
        PageInfo<TeacherLesson> mainPageInfo = teacherLessonService.teacherLessonSelect(page,pageSize,teacherLesson);
        TeacherLessonSchema teacherLessonSchema = new TeacherLessonSchema();
        if (mainPageInfo!= null){
            //分页后包装
            teacherLessonSchema.setData(mainPageInfo.getList());
            teacherLessonSchema.setSuccess(true);
            teacherLessonSchema.setTotal(mainPageInfo.getTotal()+"");
//            System.out.println(FastJsonTools.toJSONString(studentSchema));
            return FastJsonTools.toJSONString(teacherLessonSchema);

        }
        teacherLessonSchema.setMessage("无法获取");
        teacherLessonSchema.setTotal("0");
        return FastJsonTools.toJSONString(teacherLessonSchema);
    }


    @RequestMapping("insert")
    @ResponseBody
    public String  teacherLessonInsert(@RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        TeacherLesson teacherLesson = new TeacherLesson();
        teacherLesson.setLessonId((String) map.get("lessonId"));
        teacherLesson.setTeacherId((String) map.get("teacherId"));

        teacherLesson = teacherLessonService.teacherLessonInsert(teacherLesson);

        TeacherLessonSchemaOne teacherLessonSchemaOne = new TeacherLessonSchemaOne();
        teacherLessonSchemaOne.setData(teacherLesson);
        teacherLessonSchemaOne.setSuccess(true);
        //TODO 注意修改return
        return FastJsonTools.toJSONString(teacherLessonSchemaOne);
    }

    @RequestMapping(value = "update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  teacherLessonUpdate(@RequestParam("keys") String[] keys, @RequestBody String json){
        //解析json
        Map map = FastJsonTools.stringToCollect(json);
        TeacherLesson teacherLesson = new TeacherLesson();
        teacherLesson.setLessonId((String) map.get("lessonId"));
        teacherLesson.setTeacherId((String) map.get("teacherId"));

        int data = teacherLessonService.chdStudentUpdate(keys,teacherLesson);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(data+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }


    @RequestMapping("delete")
    @ResponseBody
    public String  teacherLessonDelete(String[] keys){
        int i = teacherLessonService.teacherLessonDelete(keys);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(i+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }


}
