package com.webmagic.web.dao.impl;

import com.webmagic.web.dao.SpiderDataConfigDAO;
import com.webmagic.web.model.vo.SchoolVO;
import com.webmagic.web.model.vo.SpiderDataConfigVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cuidong on 2017/7/19 0019.
 * 爬虫配置
 */
@Repository
public class SpiderDataConfigDAOImpl implements SpiderDataConfigDAO {


    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sst;

    private final String namespace="T.spiderDateConfig.";

    @Override
    public List<SpiderDataConfigVO> queryAllSpiderConfig() {
        return sst.selectList(namespace+"queryAllSpiderConfig");
    }

    @Override
    public SchoolVO getBySchoolId(Integer schoolId) {
        return sst.selectOne(namespace + "getBySchoolId",schoolId);
    }
}
