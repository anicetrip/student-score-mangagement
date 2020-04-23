package com.chd.score.demo.service;

import com.chd.score.demo.bean.ChdCollege;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CollegeService {
    PageInfo<ChdCollege> chdCollegeSelect(int page, int pageSize, String collegeId);

    ChdCollege chdCollegeInsert(String collegeName);

    int chdCollegeUpdate(String[] keys, String collegeName);
}
