package com.chd.score.demo.controller;
import com.chd.score.demo.bean.WebStruct;
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

    WebStruct webStruct = new WebStruct();

    @RequestMapping("/api/getCurrentUser")
    @ResponseBody
    public WebStruct getCurrentUser(){

        webStruct.setData("guest");
        webStruct.setSuccess(false);
//        result.put("code",0);//这行报错因为去除必须登录后上下两个相同了
//        result.put("data","guest");
//        result.put("message","");
//        result.put("success",true);
//        result.put("total",null);

        System.out.println(webStruct.toString() +"get");
        return webStruct;
    }

    @RequestMapping("/api/login")
    @ResponseBody
    public WebStruct login(@Param("username") String  username,@Param("password") String  password){
        //询问mapper
        String  teacherName = teacherService.login(username,password);
        if (StringUtils.isNotBlank(teacherName)){
            webStruct.setData(teacherName);
            webStruct.setSuccess(true);
//            result.put("code",0);
//            result.put("data",teacherName);
//            result.put("message","");
//            result.put("success",true);
//            result.put("total",null);
            System.out.println(webStruct.toString()+"get");
            return webStruct;
        }
//
        webStruct.setMessage("账户或密码错误");
        return webStruct;
    }


    @RequestMapping("/api/logout")
    public String logout(){
        return "redirect:http://localhost:8080/";
    }
}
