package com.webmagic.web.dao.impl;

import com.webmagic.web.dao.SpiderDataAlarmDAO;
import com.webmagic.web.model.SpiderDataAlarm;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shiqingwen
 * @Date 2017/11/24 13:56
 */
@Repository
public class SpiderDataAlarmDAOImpl implements SpiderDataAlarmDAO {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sst;

    private final String namespace="T.SpiderDataAlarm.";

    @Override
    public SpiderDataAlarm getByUrlAndSchoolId(int schoolId, String url) {
        Map<String,Object> map = new HashMap<>();
        map.put("schoolId",schoolId);
        map.put("url",url);
        return sst.selectOne(namespace + "getByUrlAndSchoolId",map);
    }

    @Override
    public int insert(SpiderDataAlarm spiderDataAlarm) {
        return sst.insert(namespace + "insert",spiderDataAlarm);
    }
}
