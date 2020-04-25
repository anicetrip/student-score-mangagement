package com.chd.score.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.WebStruct;
import com.chd.score.demo.service.CollegeService;
import com.chd.score.demo.service.TeacherService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.CollegeSchema;
import com.chd.score.demo.webbean.CollegeSchemaOne;
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
@RequestMapping("/api/chdCollege")
public class CollegeController {

    @Autowired
    CollegeService collegeService;

    @RequestMapping("schema")
    @ResponseBody
    public WebStruct  chdCollegeSchema(){
        String data = "  querySchema: [  // 可选，如果返回这个字段，说明服务端要更新querySchema\n" +
                "           // 结构跟{tableName}.querySchema.js中定义的是完全一样的\n" +
                "        ],\n" +
                "        dataSchema: [  // 可选，如果返回这个字段，说明服务端要更新dataSchema\n" +
                "           // 结构跟{tableName}.dataSchema.js完全一样\n" +
                "        ],";
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setData(data);
        webStruct.setMessage("测试");
//        System.out.println("post");
        return webStruct;
    }


    @RequestMapping("select")
    @ResponseBody
    public String  chdCollegeSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        String collegeId = (String) map.get("collegeId");
        //处理传参
        PageInfo<ChdCollege> collegePageInfo = collegeService.chdCollegeSelect(page,pageSize,collegeId);
        CollegeSchema schemaCollege = new CollegeSchema();
        if (collegePageInfo!= null){
            //分页后包装
            schemaCollege.setData(collegePageInfo.getList());
            schemaCollege.setSuccess(true);
            schemaCollege.setTotal(collegePageInfo.getTotal()+"");
            return FastJsonTools.toJSONString(schemaCollege);

        }
        schemaCollege.setMessage("无法获取");
        schemaCollege.setTotal("0");
        return FastJsonTools.toJSONString(schemaCollege);
    }





    @RequestMapping("insert")
    @ResponseBody
    public String  chdCollegeInsert(@RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        String collegeName = (String) map.get("collegeName");

        ChdCollege chdCollege = collegeService.chdCollegeInsert(collegeName);

        CollegeSchemaOne collegeSchemaOne = new CollegeSchemaOne();
        collegeSchemaOne.setData(chdCollege);
        collegeSchemaOne.setSuccess(true);
        //TODO 注意修改return
        return FastJsonTools.toJSONString(collegeSchemaOne);
    }


    @RequestMapping(value = "update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  chdCollegeUpdate(@RequestParam("keys") String[] keys,  @RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        int data = collegeService.chdCollegeUpdate(keys,map.get("collegeName").toString());

        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(data+"");
        webStruct.setTotal(null);

        return FastJsonTools.toJSONString(webStruct);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String  chdCollegeDelete(String[] keys){
        int i = collegeService.chdCollegeDelete(keys);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(i+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }


}
