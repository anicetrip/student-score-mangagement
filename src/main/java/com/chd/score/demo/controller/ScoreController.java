package com.chd.score.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chd.score.demo.bean.*;
import com.chd.score.demo.service.*;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/api/chdScore")
public class ScoreController {

    @Autowired
    ScoreService scoreService;
    @Autowired
    StudentService studentService;

    @RequestMapping("select")
    @ResponseBody
    public String  chdScoreSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        ChdScore chdScore = new ChdScore();
        chdScore.setStudentClassId((String) map.get("studentClassId"));
        chdScore.setClassId((String) map.get("classId"));

        //处理传参
        PageInfo<ChdScore> mainPageInfo = scoreService.chdScoreSelect(page,pageSize,chdScore);
        ScoreSchema scoreSchema = new ScoreSchema();
        if (mainPageInfo!= null){
            //分页后包装
            scoreSchema.setData(mainPageInfo.getList());
            scoreSchema.setSuccess(true);
            scoreSchema.setTotal(mainPageInfo.getTotal()+"");
            return FastJsonTools.toJSONString(scoreSchema);

        }
        scoreSchema.setMessage("无法获取");
        scoreSchema.setTotal("0");
        return FastJsonTools.toJSONString(scoreSchema);
    }
    //
//
//
//TODO 晚饭吃完回来改
//
    @RequestMapping("insert")
    @ResponseBody
    public String  chdScoreInsert(@RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        ChdScore chdScore = new ChdScore();
        chdScore.setStudentClassId((String) map.get("studentClassId"));
        chdScore.setClassId((String) map.get("classId"));
        chdScore.setStudentSchoolId((String) map.get("studentSchoolId"));
        chdScore.setTeacherId((String) map.get("teacherId"));
        chdScore.setScore((String) map.get("score"));
        chdScore.setLessonId((String) map.get("lessonId"));
        chdScore = scoreService.chdScoreInsert(chdScore);


        //TODO 新建类
        ScoreSchemaOne scoreSchemaOne = new ScoreSchemaOne();
        if (chdScore == null){
            scoreSchemaOne.setSuccess(false);
            scoreSchemaOne.setMessage("系院不匹配或者未完整填写");
            return FastJsonTools.toJSONString(scoreSchemaOne);
        }
        scoreSchemaOne.setData(chdScore);
        scoreSchemaOne.setSuccess(true);
        return FastJsonTools.toJSONString(scoreSchemaOne);
    }

    /**
     *
     * @param keys
     * @param json
     * @return
     */
    @RequestMapping(value = "update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  chdScoreUpdate(@RequestParam("keys") String[] keys, @RequestBody String json){
        //解析json
        Map map = FastJsonTools.stringToCollect(json);
        ChdScore chdScore = new ChdScore();
        chdScore.setStudentClassId((String) map.get("studentClassId"));
        chdScore.setClassId((String) map.get("classId"));
        chdScore.setStudentSchoolId((String) map.get("studentSchoolId"));
        chdScore.setTeacherId((String) map.get("teacherId"));
        chdScore.setScore((String) map.get("score"));
        chdScore.setLessonId((String) map.get("lessonId"));

        int data = scoreService.chdScoreUpdate(keys,chdScore);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(data+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }


    @RequestMapping("delete")
    @ResponseBody
    public String  chdScoreDelete(String[] keys){
        int i = scoreService.chdScoreDelete(keys);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(i+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }
}
