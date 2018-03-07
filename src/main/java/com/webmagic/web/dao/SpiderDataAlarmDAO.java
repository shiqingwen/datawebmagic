package com.webmagic.web.dao;

import com.webmagic.web.model.SpiderDataAlarm;

/**
 * @author shiqingwen
 * @Date 2017/11/24 13:55
 */
public interface SpiderDataAlarmDAO {

    /**
     *  根据学校id和url
     * @param schoolId
     * @param url
     * @return
     */
    SpiderDataAlarm getByUrlAndSchoolId(int schoolId, String url);

    /**
     * 插入警报表
     * @param spiderDataAlarm
     * @return
     */
    int insert(SpiderDataAlarm spiderDataAlarm);
}
