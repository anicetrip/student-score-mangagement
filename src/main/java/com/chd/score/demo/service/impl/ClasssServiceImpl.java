package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdClass;
import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.ChdDirect;
import com.chd.score.demo.bean.ChdMain;
import com.chd.score.demo.mapper.ClassMapper;
import com.chd.score.demo.mapper.CollegeMapper;
import com.chd.score.demo.mapper.MainMapper;
import com.chd.score.demo.service.ClasssService;
import com.chd.score.demo.util.PasswordGenerator;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class ClasssServiceImpl implements ClasssService {
    @Autowired
    CollegeMapper collegeMapper;
    @Autowired
    MainMapper mainMapper;
    @Autowired
    ClassMapper classMapper;


    @Override
    public Schema chdClasssSchema() {
        //第一个参数组DataSchemaBean
        Schema.DataBean.DataSchemaBean data1 = new Schema.DataBean.DataSchemaBean();
        //TODO 修改基础值
        data1.setKey("collegeId");
        data1.setTitle("学院");
        data1.setDataType("varchar");
        data1.setPrimary(false);
        data1.setShowInTable(true);
        data1.setDisabled(false);
        data1.setShowInForm(true);
        data1.setShowType("select");

        //data选项组ListOptionBean
        List<Schema.DataBean.OptionsBean> dataOptions = new ArrayList<>();
        //TODO 新建需要用来处理第一个form的内容,然后填充kv值
        List<ChdCollege> dataCollegeList = collegeMapper.selectAll();
        for (ChdCollege chdCollege : dataCollegeList) {
            //TODO 这里新建了一个提示用的str,用来提示之后的内容,希望不要超过数字
            String str = "";
            ChdMain chdMain = new ChdMain();
            chdMain.setCollegeId(chdCollege.getCollegeId());
            List<ChdMain> mappers = mainMapper.select(chdMain);
            for (ChdMain mapper : mappers) {
                str += " "+mapper.getMainId()+mapper.getMainName();
            }

            Schema.DataBean.OptionsBean option = new Schema.DataBean.OptionsBean();
            //TODO 注意修改需要的内容
            option.setKey(chdCollege.getCollegeId());
            option.setValue(chdCollege.getCollegeName()+str);
            dataOptions.add(option);
        }
        //将ListOptionBean封装进入DataSchemaBean并list化
        data1.setOptions(dataOptions);
        List<Schema.DataBean.DataSchemaBean> dataSchemaBeans = new ArrayList<>();
        dataSchemaBeans.add(data1);
        //第一个列表组QuerySchemaBean
        Schema.DataBean.QuerySchemaBean query1 = new Schema.DataBean.QuerySchemaBean();
        //TODO 根据情况进行修改
        query1.setKey("collegeId");
        query1.setTitle("学院");
        query1.setDataType("varchar");
        query1.setShowType("select");
        //query选项组ListOptionBean
        List<Schema.DataBean.OptionsBean> queryOptions = new ArrayList<>();
        //TODO 查找并植入,注意修改对象
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
    public PageInfo<ChdClass> chdClassSelect(int page, int pageSize, ChdClass chdClass) {
        PageInfo<ChdClass> mainPageInfo = PageHelper.startPage(page, pageSize)
                .doSelectPageInfo(() ->  this.classMapper.select(chdClass));
        return mainPageInfo;
    }




    @Override
    public ChdClass chdClasssInsert(ChdClass chdClass) {
        if(chdClass.getMainId()==null && chdClass.getDirectId() == null){
            return null;
        }
        {
            int i = 0;//判断是否有的标示位
            //判断是否这个院中有这个系
            ChdMain chdMain1 = new ChdMain();
            chdMain1.setCollegeId(chdClass.getCollegeId());
            List<ChdMain> select1 = mainMapper.select(chdMain1);
            for (ChdMain chdMain : select1) {
                if (Integer.valueOf(chdMain.getMainId()).intValue()  == Integer.valueOf(chdClass.getMainId()).intValue() ){
                    i = 1;
                }
            }
            if (i != 1){
                return null;
            }
        }
        PasswordGenerator passwordGenerator = new PasswordGenerator(8,8);
        Calendar cal = Calendar.getInstance();
        String create = cal.getTime()+passwordGenerator.generateRandomPassword();
        //TODO 修改类名
        chdClass.setIsCreate(create);
        List<ChdClass> select = classMapper.select(chdClass);
        //TODO 修改班级号
        String classId = select.size()== 0
                ? cal.get(Calendar.YEAR)+chdClass.getCollegeId()+chdClass.getMainId()+"01"
                :Integer.parseInt(select.get(select.size()-1).getClassId())+1+"";



        chdClass.setClassId(classId);


        int i = classMapper.insertSelective(chdClass);
        chdClass = classMapper.selectOne(chdClass);
        //不要把创建用于查询的内部码发送出去
//        chdClass.setIsCreate("yes");
        return chdClass;
    }
    @Override
    public int chdClassUpdate(String[] keys, ChdClass chdClass) {
        int i = 0;
        for (String key : keys) {
            chdClass.setIsCreate(key);
            //TODO 记得修改类
            Example e = new Example(ChdClass.class);
            e.createCriteria().andEqualTo("isCreate",key);
            //TODO 记得修改mapper的类型
            i += classMapper.updateByExampleSelective(chdClass, e);
        }
        return i;
    }

    @Override
    public int chdClassDelete(String[] keys) {
        int i = 0;
        for (String key : keys) {
             i += classMapper.deleteByPrimaryKey(key);
        }
        return i;
    }
}

