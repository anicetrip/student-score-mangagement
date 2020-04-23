package com.chd.score.demo.service;

import com.chd.score.demo.bean.ChdCollege;
import com.chd.score.demo.bean.ChdMain;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageInfo;

public interface MainService {
    Schema chdMainSchema();


    PageInfo<ChdMain> chdCollegeSelect(int page, int pageSize, ChdMain chdMain);

    ChdMain chdMainInsert(ChdMain chdMain);

    int chdMainUpdate(String[] keys, ChdMain chdMain);
}
