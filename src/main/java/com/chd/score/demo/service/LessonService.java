package com.chd.score.demo.service;

import com.chd.score.demo.bean.ChdLesson;
import com.chd.score.demo.webbean.Schema;
import com.github.pagehelper.PageInfo;

public interface LessonService {
    Schema chdClasssSchema();

    PageInfo<ChdLesson> chdClassSelect(int page, int pageSize, ChdLesson chdLesson);

    ChdLesson chdLessonInsert(ChdLesson chdLesson);

    int chdLessonUpdate(String[] keys, ChdLesson chdLesson);

    int chdLessonDelete(String[] keys);
}
