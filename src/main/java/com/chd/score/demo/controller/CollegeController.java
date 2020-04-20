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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class CollegeController {

    @Autowired
    CollegeService collegeService;

    @RequestMapping("api/chdCollege/select")
    @ResponseBody
    public String  chdCollegeSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        System.out.println(map.size());
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        String collegeId = (String) map.get("collegeId");
        CollegeSchema schemaCollege = new CollegeSchema();
        List<ChdCollege> collegeList = collegeService.chdCollegeSelect(page,pageSize,collegeId);
        if (collegeList!= null){
            //TODO  需要新建一个collegeSchema对象,然后包装,不排除里面就能直接出来外面就可以直接包装了
            schemaCollege.setData(collegeList);
            schemaCollege.setSuccess(true);
            return FastJsonTools.toJSONString(schemaCollege);

        }
        schemaCollege.setMessage("无法获取");
        schemaCollege.setTotal(1);
        return FastJsonTools.toJSONString(schemaCollege);
    }



    @RequestMapping("/api/chdCollege/schema")
    @ResponseBody
    public WebStruct  chdCollegeSchema(){
        String data = "  querySchema: [  // 可选，如果返回这个字段，说明服务端要更新querySchema\n" +
                "           // 结构跟{tableName}.querySchema.js中定义的是完全一样的\n" +
                "        ],\n" +
                "        dataSchema: [  // 可选，如果返回这个字段，说明服务端要更新dataSchema\n" +
                "           // 结构跟{tableName}.dataSchema.js完全一样\n" +
                "        ],";


//        Map<String, String> kvMap = new Map<String, String>;
//        kvMap.put("key","服务器1");
//        String data = "";
//        data = FastJsonTools.collectToString(kvMap);


        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setData(data);
        webStruct.setMessage("测试");
//        JSONObject result = new JSONObject();
//        result.put("title",title);
//        result.put("id",id);
//        result.put("money",(int)9999999);
        System.out.println("post");
        return webStruct;
    }

}
