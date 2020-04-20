package com.chd.score.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.WebStruct;
import com.chd.score.demo.service.CollegeService;
import com.chd.score.demo.service.TeacherService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.CollegeSchema;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class DemoController {
//    chdCollege/select






//    @Autowired
//TeacherService teacherService;
//
//    @RequestMapping("/api/getCurrentUser")
//    @ResponseBody
//    public String getCurrentUser(){
//        JSONObject  result = new JSONObject();
//        result.put("code",0);
//        result.put("data","guest");
//        result.put("message","");
//        result.put("success",false);
//        result.put("total",null);
//        System.out.println(result.toJSONString()+"get");
//        return result.toJSONString();
//    }
//
//    @RequestMapping("/api/login")
//    @ResponseBody
//    public String login(@Param("username") String  username,@Param("password") String  password){
//        JSONObject  result = new JSONObject();
//        //询问mapper
//        boolean login = teacherService.login(username,password);
//        if (login){
//            result.put("code",0);
//            result.put("data","guest");
//            result.put("message","");
//            result.put("success",true);
//            result.put("total",null);
//            System.out.println(result.toJSONString()+"get");
//            return result.toJSONString();
//        }
//
//        result.put("message","账户或者密码错误");
//        return result.toJSONString();
//    }
//
//
//    @RequestMapping("/api/logout")
//    public String logout(){
//        return "redirect:http://localhost:8080/";
//    }




    @RequestMapping("index1")
    @ResponseBody
    public String index1(){
        return "hello ajax";
    }






    @RequestMapping("index2")
    @ResponseBody
    public String index2(String id,String title){
        JSONObject  result = new JSONObject();
        result.put("id",id);
        result.put("title",title);
        System.out.println(result.toJSONString()+"get");
        return result.toJSONString();
    }

    @RequestMapping("index3")
    @ResponseBody
    public String index3(@Param("id") int id,@Param("title") String  title){
        JSONObject result = new JSONObject();
        result.put("title",title);
        result.put("id",id);
        result.put("money",(int)9999999);
        System.out.println(result.toJSONString()+"post");
        return result.toJSONString();
    }

//    {"code":0,"data":"guest","message":"","success":true,"total":null}
///api/chdCollege/schema



}
