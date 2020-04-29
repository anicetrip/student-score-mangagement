package com.chd.score.demo.controller;

import com.chd.score.demo.bean.ChdClass;
import com.chd.score.demo.bean.ChdLesson;
import com.chd.score.demo.bean.WebStruct;
import com.chd.score.demo.service.LessonService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.ClassSchemaOne;
import com.chd.score.demo.webbean.LessonChemaOne;
import com.chd.score.demo.webbean.LessonSchema;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/api/chdLesson")
public class LessonController {
    @Autowired
    LessonService lessonService;



    @RequestMapping("schema")
    @ResponseBody
    public String  chdLessonSchema(){
        Schema schema = lessonService.chdClasssSchema();
        return FastJsonTools.toJSONString(schema);
    }

    @RequestMapping("select")
    @ResponseBody
    public String  chdLessonSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        ChdLesson chdLesson = new ChdLesson();
        chdLesson.setLessonCollegeId((String) map.get("lessonCollegeId"));
        chdLesson.setLessonName((String) map.get("lessonName"));
        //处理传参
        PageInfo<ChdLesson> mainPageInfo = lessonService.chdClassSelect(page,pageSize,chdLesson);

        //TODO lessonScheme
        LessonSchema lessonSchema = new LessonSchema();
        if (mainPageInfo!= null){
            //分页后包装
            lessonSchema.setData(mainPageInfo.getList());
            lessonSchema.setSuccess(true);
            lessonSchema.setTotal(mainPageInfo.getTotal()+"");
            return FastJsonTools.toJSONString(lessonSchema);

        }
        lessonSchema.setMessage("无法获取");
        lessonSchema.setTotal("0");
        return FastJsonTools.toJSONString(lessonSchema);
    }

    @RequestMapping("insert")
    @ResponseBody
    public String  chdLessonInsert(@RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        ChdLesson chdLesson = new ChdLesson();
        chdLesson.setLessonName((String) map.get("lessonName"));
        chdLesson.setLessonCollegeId((String) map.get("lessonCollegeId"));
        chdLesson.setLessonGpa((String) map.get("lessonGpa"));
        chdLesson = lessonService.chdLessonInsert(chdLesson);
        //TODO 新建类
        LessonChemaOne lessonChemaOne = new LessonChemaOne();
        if (chdLesson == null){
            lessonChemaOne.setSuccess(false);
            lessonChemaOne.setMessage("系院不匹配或者未完整填写");
            return FastJsonTools.toJSONString(lessonChemaOne);
        }
        lessonChemaOne.setData(chdLesson);
        lessonChemaOne.setSuccess(true);
        return FastJsonTools.toJSONString(lessonChemaOne);
    }

    @RequestMapping(value = "update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  chdLessonUpdate(@RequestParam("keys") String[] keys, @RequestBody String json){
        //解析json
        Map map = FastJsonTools.stringToCollect(json);
        ChdLesson chdLesson = new ChdLesson();
        chdLesson.setLessonName((String) map.get("lessonName"));
        chdLesson.setLessonCollegeId((String) map.get("lessonCollegeId"));
        chdLesson.setLessonGpa((String) map.get("lessonGpa"));

        int data = lessonService.chdLessonUpdate(keys,chdLesson);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(data+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String  chdLessonDelete(String[] keys){
        int i = lessonService.chdLessonDelete(keys);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(i+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }





}
