package com.chd.score.demo.controller;
import com.chd.score.demo.bean.*;
import com.chd.score.demo.service.ClasssService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/api/chdClasss")
public class ClasssController {
    @Autowired
    ClasssService classsService;

    @RequestMapping("schema")
    @ResponseBody
    public String  chdClassSchema(){
        Schema schema = classsService.chdClasssSchema();
        return FastJsonTools.toJSONString(schema);
    }


    @RequestMapping("select")
    @ResponseBody
    public String  chdClassSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        ChdClass chdClass = new ChdClass();
        chdClass.setCollegeId((String) map.get("collegeId"));
        chdClass.setMainId((String) map.get("MainId"));

        //处理传参
        PageInfo<ChdClass> mainPageInfo = classsService.chdClassSelect(page,pageSize,chdClass);
        ClassSchema classSChema = new ClassSchema();
        if (mainPageInfo!= null){
            //分页后包装
            classSChema.setData(mainPageInfo.getList());
            classSChema.setSuccess(true);
            classSChema.setTotal(mainPageInfo.getTotal()+"");
            return FastJsonTools.toJSONString(classSChema);

        }
        classSChema.setMessage("无法获取");
        classSChema.setTotal("0");
        return FastJsonTools.toJSONString(classSChema);
    }
    //
//
//
//
//
    @RequestMapping("insert")
    @ResponseBody
    public String  chdClasssInsert(@RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        ChdClass chdClass = new ChdClass();
        chdClass.setClassId((String) map.get("classId"));
        chdClass.setCollegeId((String) map.get("collegeId"));
        chdClass.setMainId((String) map.get("mainId"));
        chdClass.setDirectId((String) map.get("directId"));
        chdClass = classsService.chdClasssInsert(chdClass);
        //TODO 新建类
        ClassSchemaOne classSchemaOne = new ClassSchemaOne();
        if (chdClass == null){
            classSchemaOne.setSuccess(false);
            classSchemaOne.setMessage("系院不匹配或者未完整填写");
            return FastJsonTools.toJSONString(classSchemaOne);
        }
        classSchemaOne.setData(chdClass);
        classSchemaOne.setSuccess(true);
        return FastJsonTools.toJSONString(classSchemaOne);
    }

    /**
     *
     * @param keys
     * @param json
     * @return
     */
    @RequestMapping(value = "update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  chdClassUpdate(@RequestParam("keys") String[] keys, @RequestBody String json){
        //解析json
        Map map = FastJsonTools.stringToCollect(json);
        ChdClass chdClass = new ChdClass();
        chdClass.setClassId((String) map.get("classId"));
        chdClass.setCollegeId((String) map.get("collegeId"));
        chdClass.setDirectId((String) map.get("directId"));
        chdClass.setMainId((String) map.get("mainId"));

        int data = classsService.chdClassUpdate(keys,chdClass);
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
        int i = classsService.chdClassDelete(keys);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(i+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }

}
