package com.chd.score.demo.service;

import com.chd.score.demo.bean.ChdClass;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageInfo;

public interface ClasssService {
    Schema chdClasssSchema();


    ChdClass chdClasssInsert(ChdClass chdClass);

    PageInfo<ChdClass> chdClassSelect(int page, int pageSize, ChdClass chdClass);

    int chdClassUpdate(String[] keys, ChdClass chdClass);

    int chdClassDelete(String[] keys);
}
