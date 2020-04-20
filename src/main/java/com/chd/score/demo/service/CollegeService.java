package com.chd.score.demo.service;

import com.chd.score.demo.bean.ChdCollege;

import java.util.List;

public interface CollegeService {
    List<ChdCollege> chdCollegeSelect(int page, int pageSize, String collegeId);
}
