package com.chd.score.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.chd.score.demo.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@CrossOrigin
public class LoginController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping("/api/getCurrentUser")
    @ResponseBody
    public String getCurrentUser(){
        JSONObject  result = new JSONObject();
        result.put("code",0);//这行报错因为去除必须登录后上下两个相同了
        result.put("data","guest");
        result.put("message","");
        result.put("success",false);
        result.put("total",null);
        System.out.println(result.toJSONString()+"get");
        return result.toJSONString();
    }

    @RequestMapping("/api/login")
    @ResponseBody
    public String login(@Param("username") String  username,@Param("password") String  password){
        JSONObject  result = new JSONObject();
        //询问mapper
        String  teacherName = teacherService.login(username,password);
        if (StringUtils.isNotBlank(teacherName)){
            result.put("code",0);
            result.put("data",teacherName);
            result.put("message","");
            result.put("success",true);
            result.put("total",null);
            System.out.println(result.toJSONString()+"get");
            return result.toJSONString();
        }

        result.put("message","账户或者密码错误");
        return result.toJSONString();
    }


    @RequestMapping("/api/logout")
    public String logout(){
        return "redirect:http://localhost:8080/";
    }
}
