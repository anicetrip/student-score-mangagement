package com.chd.score.demo.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.ChdLesson;
import com.chd.score.demo.bean.WebStruct;
import com.chd.score.demo.service.CollegeService;
import com.chd.score.demo.service.TeacherService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.CollegeSchema;
import com.chd.score.demo.webbean.CollegeSchemaOne;
import com.chd.score.demo.webbean.YysbLogin;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/yysb")
public class NewLoginController {
    @RequestMapping("login")
    @ResponseBody
    public String  login(@Param("username") String  username,@Param("password") String  password){
        YysbLogin yysbLogin = new YysbLogin();
        yysbLogin.setSuccess(true);
        yysbLogin.setTeacherId("12");
        yysbLogin.setTeacherName("十二号");
        List<String> lessonId = new ArrayList<>();
            lessonId.add("60");
            lessonId.add("61");
        yysbLogin.setLessonId(lessonId);
        List<String> lessonName = new ArrayList<>();
            lessonName.add("数学");
            lessonName.add("英语");
        yysbLogin.setLessonName(lessonName);
        System.out.println(FastJsonTools.toJSONString(yysbLogin));
        return FastJsonTools.toJSONString(yysbLogin);
    }
}
