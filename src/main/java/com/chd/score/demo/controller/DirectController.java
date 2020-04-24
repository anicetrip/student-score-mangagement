package com.chd.score.demo.controller;

import com.chd.score.demo.bean.ChdDirect;
import com.chd.score.demo.bean.ChdMain;
import com.chd.score.demo.bean.WebStruct;
import com.chd.score.demo.service.DirectService;
import com.chd.score.demo.service.MainService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/api/chdDirect")
public class DirectController {
    @Autowired
    DirectService directService;

    @RequestMapping("schema")
    @ResponseBody
    public String  chdDirectSchema(){
        Schema schema = directService.chdDirectSchema();
        return FastJsonTools.toJSONString(schema);
    }


    @RequestMapping("select")
    @ResponseBody
    public String  chdDirectSelect(@RequestBody String json){
        //从接受的json字符串中获得返回的参数
        Map map = FastJsonTools.stringToCollect(json);
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        ChdDirect chdDirect = new ChdDirect();
        chdDirect.setCollegeId((String) map.get("collegeId"));
        chdDirect.setMainId((String) map.get("MainId"));

        //处理传参
        PageInfo<ChdDirect> mainPageInfo = directService.chdDirectSelect(page,pageSize,chdDirect);
        DirectSchema directSchema = new DirectSchema();
        if (mainPageInfo!= null){
            //分页后包装
            directSchema.setData(mainPageInfo.getList());
            directSchema.setSuccess(true);
            directSchema.setTotal(mainPageInfo.getTotal()+"");
            return FastJsonTools.toJSONString(directSchema);

        }
        directSchema.setMessage("无法获取");
        directSchema.setTotal("0");
        return FastJsonTools.toJSONString(directSchema);
    }
    //
//
//
//
//
    @RequestMapping("insert")
    @ResponseBody
    public String  chdDirectInsert(@RequestBody String json){
        Map map = FastJsonTools.stringToCollect(json);
        ChdDirect chdDirect = new ChdDirect();
        chdDirect.setDirectName((String) map.get("directName"));
        chdDirect.setCollegeId((String) map.get("collegeId"));
        chdDirect.setMainId((String) map.get("mainId"));
        chdDirect = directService.chdDirectInsert(chdDirect);
        DirectSchemaOne directSchemaOne = new DirectSchemaOne();
        if (chdDirect == null){
            directSchemaOne.setSuccess(false);
            directSchemaOne.setMessage("系院不匹配或者未完整填写");
            directSchemaOne.setMessage("新建行:"+chdDirect.getDirectName()+chdDirect.getDirectId());
            return FastJsonTools.toJSONString(directSchemaOne);
        }
        directSchemaOne.setData(chdDirect);
        directSchemaOne.setSuccess(true);
        return FastJsonTools.toJSONString(directSchemaOne);
    }

    /**
     *
     * @param keys
     * @param json
     * @return
     */
    //TODO
    @RequestMapping(value = "update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  chdDirectUpdate(@RequestParam("keys") String[] keys, @RequestBody String json){
        //解析json
        Map map = FastJsonTools.stringToCollect(json);
        ChdDirect chdDirect = new ChdDirect();
        chdDirect.setDirectName((String) map.get("directName"));
        int data = directService.chdDirectUpdate(keys,chdDirect);
        WebStruct webStruct = new WebStruct();
        webStruct.setSuccess(true);
        webStruct.setTotal(null);
        webStruct.setData(data+"");
        webStruct.setTotal(null);

        return FastJsonTools.toJSONString(webStruct);

    }
}
