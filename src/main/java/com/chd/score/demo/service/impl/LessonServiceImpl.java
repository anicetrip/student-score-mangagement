package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdClass;
import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.ChdLesson;
import com.chd.score.demo.bean.ChdMain;
import com.chd.score.demo.mapper.CollegeMapper;
import com.chd.score.demo.mapper.LessonMapper;
import com.chd.score.demo.service.LessonService;
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
public class LessonServiceImpl implements LessonService {
    @Autowired
    CollegeMapper collegeMapper;
    @Autowired
    LessonMapper lessonMapper;

    @Override
    public Schema chdClasssSchema() {
        //第一个参数组DataSchemaBean
        Schema.DataBean.DataSchemaBean data1 = new Schema.DataBean.DataSchemaBean();
        data1.setKey("lessonCollegeId");
        data1.setTitle("开课学院");
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
        query1.setKey("lessonCollegeId");
        query1.setTitle("开课学院");
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
    public PageInfo<ChdLesson> chdClassSelect(int page, int pageSize, ChdLesson chdLesson) {
        PageInfo<ChdLesson> mainPageInfo = PageHelper.startPage(page, pageSize)
                .doSelectPageInfo(() -> this.lessonMapper.select(chdLesson));
        return mainPageInfo;
    }

    @Override
    public ChdLesson chdLessonInsert(ChdLesson chdLesson) {
        PasswordGenerator passwordGenerator = new PasswordGenerator(8,3);
        Calendar cal = Calendar.getInstance();
        String create = cal.getTime()+passwordGenerator.generateRandomPassword();
        //TODO 修改课程编号
        String classId = cal.get(Calendar.YEAR)+chdLesson.getLessonCollegeId()+ passwordGenerator.generateRandomPassword();



        chdLesson.setLessonId(classId);


        int i = lessonMapper.insertSelective(chdLesson);
        chdLesson = lessonMapper.selectOne(chdLesson);
        //不要把创建用于查询的内部码发送出去
//        chdClass.setIsCreate("yes");
        return chdLesson;
    }

    @Override
    public int chdLessonUpdate(String[] keys, ChdLesson chdLesson) {
        int i = 0;
        for (String key : keys) {
            chdLesson.setLessonId(key);
            //TODO 记得修改类
            Example e = new Example(ChdLesson.class);
            e.createCriteria().andEqualTo("lessonId",key);
            //TODO 记得修改mapper的类型
            i += lessonMapper.updateByExampleSelective(chdLesson, e);
        }
        return i;
    }

    @Override
    public int chdLessonDelete(String[] keys) {
        int i = 0;
        for (String key : keys) {
            i += lessonMapper.deleteByPrimaryKey(key);
        }
        return i;
    }
}
