package com.chd.score.demo.service;

import com.chd.score.demo.bean.ChdClass;
import com.chd.score.demo.bean.ChdStudent;
import com.github.pagehelper.PageInfo;

public interface StudentService {
    PageInfo<ChdStudent> chdStudentSelect(int page, int pageSize, ChdStudent chdStudent);

    ChdStudent chdStudentInsert(ChdStudent chdStudent);

    int chdClassDelete(String[] keys);

    int chdStudentUpdate(String[] keys, ChdStudent chdStudent);
}
