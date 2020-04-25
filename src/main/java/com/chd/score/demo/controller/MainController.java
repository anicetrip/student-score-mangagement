package com.chd.score.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.ChdMain;
import com.chd.score.demo.bean.WebStruct;
import com.chd.score.demo.service.CollegeService;
import com.chd.score.demo.service.MainService;
import com.chd.score.demo.service.TeacherService;
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
@RequestMapping("/api/chdMain")


public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping("schema")
    @ResponseBody
    public String  chdMainSchema(){
        Schema schema = mainService.chdMainSchema();
        return FastJsonTools.toJSONString(schema);
    }


    @RequestMapping("select")
    @ResponseBody
    public String  chdMainSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        ChdMain chdMain = new ChdMain();
        chdMain.setCollegeId((String) map.get("collegeId"));
        chdMain.setMainId((String) map.get("MainId"));
        //处理传参
        PageInfo<ChdMain> mainPageInfo = mainService.chdCollegeSelect(page,pageSize,chdMain);
        MainScheme mainScheme = new MainScheme();
        if (mainPageInfo!= null){
            //分页后包装
            mainScheme.setData(mainPageInfo.getList());
            mainScheme.setSuccess(true);
            mainScheme.setTotal(mainPageInfo.getTotal()+"");
            return FastJsonTools.toJSONString(mainScheme);

        }
        mainScheme.setMessage("无法获取");
        mainScheme.setTotal("0");
        return FastJsonTools.toJSONString(mainScheme);
    }
//
//
//
//
//
    @RequestMapping("insert")
    @ResponseBody
    public String  chdMainInsert(@RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        ChdMain chdMain = new ChdMain();
        chdMain.setMainName((String) map.get("mainName"));
        chdMain.setCollegeId((String) map.get("collegeId"));

        chdMain = mainService.chdMainInsert(chdMain);

        MainSchemaOne mainSchemaOne = new MainSchemaOne();
        mainSchemaOne.setData(chdMain);
        mainSchemaOne.setSuccess(true);
        //TODO 注意修改return
        return FastJsonTools.toJSONString(mainSchemaOne);
    }

    @RequestMapping(value = "update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  chdMainUpdate(@RequestParam("keys") String[] keys,  @RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        ChdMain chdMain = new ChdMain();
        chdMain.setMainName((String) map.get("mainName"));
        System.out.println(chdMain.getMainName());
        chdMain.setCollegeId((String) map.get("collegeId"));
        System.out.println(chdMain.getCollegeId());
        int data = mainService.chdMainUpdate(keys,chdMain);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(data+"");
        webStruct.setTotal(null);

        return FastJsonTools.toJSONString(webStruct);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String  chdMainDelete(String[] keys){
        int i = mainService.chdMainDelete(keys);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(i+"");
        webStruct.setTotal(null);
        return FastJsonTools.toJSONString(webStruct);
    }

}

