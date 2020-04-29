package com.chd.score.demo.service;

import com.chd.score.demo.bean.ChdScore;
import com.github.pagehelper.PageInfo;

public interface ScoreService {
    PageInfo<ChdScore> chdScoreSelect(int page, int pageSize, ChdScore chdScore);

    ChdScore chdScoreInsert(ChdScore chdScore);

    int chdScoreUpdate(String[] keys, ChdScore chdScore);

    int chdScoreDelete(String[] keys);
}
