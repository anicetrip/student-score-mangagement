package com.chd.score.demo.service.impl;

import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.mapper.CollegeMapper;
import com.chd.score.demo.service.CollegeService;
import com.chd.score.demo.util.FastJsonTools;
import com.chd.score.demo.webbean.CollegeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    CollegeMapper collegeMapper;

    @Override
    public List<ChdCollege> chdCollegeSelect(int page, int pageSize, String collegeId) {
        ChdCollege chdCollege = new ChdCollege();
        chdCollege.setCollegeId(collegeId);
        List<ChdCollege> colleges= collegeMapper.select(chdCollege);
        return colleges;
    }
}
