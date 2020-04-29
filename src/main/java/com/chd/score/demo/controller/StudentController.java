package com.chd.score.demo.controller;
import com.chd.score.demo.bean.*;
import com.chd.score.demo.service.ClasssService;
import com.chd.score.demo.service.StudentService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/api/chdStudent")
public class StudentController {
    @Autowired
    StudentService studentService;




    @RequestMapping("select")
    @ResponseBody
    public String  chdStudentSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        ChdStudent chdStudent = new ChdStudent();
        chdStudent.setStudentClass((String) map.get("studentClass"));
        //处理传参
        PageInfo<ChdStudent> mainPageInfo = studentService.chdStudentSelect(page,pageSize,chdStudent);
        StudentSchema studentSchema = new StudentSchema();
        if (mainPageInfo!= null){
            //分页后包装
            studentSchema.setData(mainPageInfo.getList());
            studentSchema.setSuccess(true);
            studentSchema.setTotal(mainPageInfo.getTotal()+"");
//            System.out.println(FastJsonTools.toJSONString(studentSchema));
            return FastJsonTools.toJSONString(studentSchema);

        }
        studentSchema.setMessage("无法获取");
        studentSchema.setTotal("0");
        return FastJsonTools.toJSONString(studentSchema);
    }
    //
//
//
//
//
    @RequestMapping("insert")
    @ResponseBody
    public String  chdStudentInsert(@RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        ChdStudent chdStudent = new ChdStudent();
        chdStudent.setStudentName((String) map.get("studentName"));
        chdStudent.setStudentClassId((String) map.get("studentClassId"));
        chdStudent.setStudentOthers((String) map.get("studentOthers"));
        chdStudent.setStudentClass((String) map.get("studentClass"));
        chdStudent.setStudentStatus((String) map.get("studentStatus"));
        chdStudent = studentService.chdStudentInsert(chdStudent);
        //TODO 新建类
        StudentSchemaOne studentSchemaOne = new StudentSchemaOne();
        if (chdStudent == null){
            studentSchemaOne.setSuccess(false);
            studentSchemaOne.setMessage("系院不匹配或者未完整填写");
            return FastJsonTools.toJSONString(studentSchemaOne);
        }
        studentSchemaOne.setData(chdStudent);
        studentSchemaOne.setSuccess(true);
        return FastJsonTools.toJSONString(studentSchemaOne);
    }

    /**
     *
     * @param keys
     * @param json
     * @return
     */
    @RequestMapping(value = "update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  chdStudentUpdate(@RequestParam("keys") String[] keys, @RequestBody String json){
        //解析json
        Map map = FastJsonTools.stringToCollect(json);
        ChdStudent chdStudent = new ChdStudent();
        chdStudent.setStudentName((String) map.get("studentName"));
        chdStudent.setStudentStatus((String) map.get("studentStatus"));
        chdStudent.setStudentOthers((String) map.get("studentOthers"));
        chdStudent.setStudentClass((String) map.get("studentClass"));

        int data = studentService.chdStudentUpdate(keys,chdStudent);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(data+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }


    @RequestMapping("delete")
    @ResponseBody
    public String  chdClassDelete(String[] keys){
        int i = studentService.chdClassDelete(keys);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(i+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }
}
