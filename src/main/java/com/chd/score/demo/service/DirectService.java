package com.chd.score.demo.service;

import com.chd.score.demo.bean.ChdDirect;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageInfo;

public interface DirectService {
    Schema chdDirectSchema();

    PageInfo<ChdDirect> chdDirectSelect(int page, int pageSize, ChdDirect chdDirect);

    ChdDirect chdDirectInsert(ChdDirect chdDirect);
}
