package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdClass;
import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.ChdDirect;
import com.chd.score.demo.bean.ChdMain;
import com.chd.score.demo.mapper.ClassMapper;
import com.chd.score.demo.mapper.CollegeMapper;
import com.chd.score.demo.mapper.DirectMapper;
import com.chd.score.demo.mapper.MainMapper;
import com.chd.score.demo.service.MainService;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    CollegeMapper collegeMapper;
    @Autowired
    MainMapper mainMapper;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    DirectMapper directMapper;

    @Override
    public Schema chdMainSchema() {



        //第一个参数组DataSchemaBean
        Schema.DataBean.DataSchemaBean data1 = new Schema.DataBean.DataSchemaBean();
        data1.setKey("collegeId");
        data1.setTitle("学院名称");
        data1.setDataType("varchar");
        data1.setPrimary(false);
        data1.setDisabled(false);
        data1.setShowInForm(true);
        data1.setShowType("select");
        data1.setShowInTable(true);
        //data选项组ListOptionBean
        List<Schema.DataBean.OptionsBean> dataOptions = new ArrayList<>();
        List<ChdCollege> dataCollegeList = collegeMapper.selectAll();
        for (ChdCollege chdCollege : dataCollegeList) {
            Schema.DataBean.OptionsBean option = new Schema.DataBean.OptionsBean();
            option.setKey(chdCollege.getCollegeId());
            option.setValue(chdCollege.getCollegeName());
            dataOptions.add(option);
        }
        //将ListOptionBean封装进入DataSchemaBean并list化
        data1.setOptions(dataOptions);
        List<Schema.DataBean.DataSchemaBean> dataSchemaBeans = new ArrayList<>();
        dataSchemaBeans.add(data1);
        //第一个列表组QuerySchemaBean
        Schema.DataBean.QuerySchemaBean query1 = new Schema.DataBean.QuerySchemaBean();
        query1.setKey("collegeId");
        query1.setTitle("学院编号");
        query1.setDataType("varchar");
        query1.setShowType("select");
        //query选项组ListOptionBean
        List<Schema.DataBean.OptionsBean> queryOptions = new ArrayList<>();
        //查找并植入,注意修改对象
        List<ChdCollege> queryCollegeList = collegeMapper.selectAll();
        for (ChdCollege chdCollege : queryCollegeList) {
            Schema.DataBean.OptionsBean options = new Schema.DataBean.OptionsBean();
            options.setKey(chdCollege.getCollegeId());
            options.setValue(chdCollege.getCollegeName());
            queryOptions.add(options);
        }
        //将ListOptionBean封装进入QuerySchemaBean并list化
        query1.setOptions(queryOptions);
        List<Schema.DataBean.QuerySchemaBean> querySchemaBeans = new ArrayList<>();
        querySchemaBeans.add(query1);
//      处理DATABEAN
        Schema.DataBean dataBean = new Schema.DataBean();
        //置入dataBean
        dataBean.setDataSchema(dataSchemaBeans);
        //置入queryBean
        dataBean.setQuerySchema(querySchemaBeans);
        //处理schema
        Schema schema = new Schema();
        schema.setData(dataBean);
        return schema;
    }

    @Override
    public PageInfo<ChdMain> chdCollegeSelect(int page, int pageSize, ChdMain chdMain) {
        PageInfo<ChdMain> mainPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("main_id asc")
                .doSelectPageInfo(() -> this.mainMapper.select(chdMain));
        return mainPageInfo;
    }

    @Override
    public ChdMain chdMainInsert(ChdMain chdMain) {
        Date date = new Date();
        String create = date.toString();
        //TODO
        chdMain.setIsCreate(create);
        int i = mainMapper.insertSelective(chdMain);
        chdMain = mainMapper.selectOne(chdMain);
        //不要把创建用于查询的内部码发送出去
//        chdMain.setIsCreate("yes");
        return chdMain;
    }

    @Override
    public int chdMainUpdate(String[] keys, ChdMain chdMain) {

        int i = 0;
        for (String key : keys) {
            chdMain.setMainId(key);
            Example e = new Example(ChdMain.class);
            e.createCriteria().andEqualTo("mainId",key);
            i += mainMapper.updateByExample(chdMain, e);
        }
        return i;
    }

    @Override
    public int chdMainDelete(String[] keys) {
        int i = 0;
        for (String key : keys) {
            ChdClass chdClass = new ChdClass();
            chdClass.setMainId(key);
            classMapper.delete(chdClass);
            ChdDirect chdDirect = new ChdDirect();
            chdDirect.setMainId(key);
            directMapper.delete(chdDirect);
            i += mainMapper.deleteByPrimaryKey(key);
        }
        return i;
    }


}
