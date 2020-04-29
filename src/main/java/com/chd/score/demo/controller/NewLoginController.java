package com.chd.score.demo.controller;
import com.chd.score.demo.service.SpeechService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.YysbLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/yysb")
public class NewLoginController {

    @Autowired
    SpeechService speechService;

    @RequestMapping("login")
    @ResponseBody
    public String  login(@Param("userID") String  userID,@Param("password") String  password){
        YysbLogin yysbLogin = speechService.login(userID,password);




//        YysbLogin yysbLogin = new YysbLogin();
//        yysbLogin.setSuccess(true);
//        yysbLogin.setTeacherId("12");
//        yysbLogin.setTeacherName("十二号");
//        List<String> lessonId = new ArrayList<>();
//            lessonId.add("60");
//            lessonId.add("61");
//        yysbLogin.setLessonId(lessonId);
//        List<String> lessonName = new ArrayList<>();
//            lessonName.add("数学");
//            lessonName.add("英语");
//        yysbLogin.setLessonName(lessonName);
//        System.out.println(FastJsonTools.toJSONString(yysbLogin));
        return FastJsonTools.toJSONString(yysbLogin);
    }
}
