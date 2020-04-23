package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.mapper.CollegeMapper;
import com.chd.score.demo.service.CollegeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    CollegeMapper collegeMapper;

    @Override
    public PageInfo<ChdCollege> chdCollegeSelect(int page, int pageSize, String collegeId) {
        ChdCollege chdCollege = new ChdCollege();
        chdCollege.setCollegeId(collegeId);
        //分页
        PageInfo<ChdCollege> collegePageInfo = PageHelper.startPage(page, pageSize).setOrderBy("college_id asc")
                .doSelectPageInfo(() -> this.collegeMapper.select(chdCollege));
//        System.out.println(collegePageInfo.getList());
        return collegePageInfo;
    }

    @Override
    public ChdCollege chdCollegeInsert(String collegeName) {
        Date date = new Date();
        String create = date.toString();
//        System.out.println(date);
        ChdCollege chdCollege = new ChdCollege();
        chdCollege.setCollegeName(collegeName);
        chdCollege.setIsCreate(create);
        int i = collegeMapper.insertSelective(chdCollege);
        chdCollege.setIsCreate(create);
        ChdCollege chdCollege1 = collegeMapper.selectOne(chdCollege);
        //不要把创建用于查询的内部码发送出去
        chdCollege1.setIsCreate("yes");
        return chdCollege1;
    }

    @Override
    public int chdCollegeUpdate(String[] keys, String collegeName) {
        ChdCollege chdCollege = new ChdCollege();
        int i = 0;
        for (String key : keys) {
            chdCollege.setCollegeId(key);
            chdCollege.setCollegeName(collegeName);

            Example e = new Example(ChdCollege.class);
            e.createCriteria().andEqualTo("collegeId",key);
            i += collegeMapper.updateByExampleSelective(chdCollege, e);
        }
        return i;
    }

}
