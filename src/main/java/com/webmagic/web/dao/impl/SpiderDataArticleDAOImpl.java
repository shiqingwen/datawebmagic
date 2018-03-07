package com.webmagic.web.dao.impl;

import com.webmagic.web.dao.SpiderDataArticleDAO;
import com.webmagic.web.model.SpiderDataArticle;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sqw
 * Created by sqw on 2017/7/24.
 */
@Repository
public class SpiderDataArticleDAOImpl implements SpiderDataArticleDAO {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sst;

    private final String namespace="T.SpiderDataArticle.";

    @Override
    public int insert(SpiderDataArticle spiderDataArticle) {
        return sst.insert(namespace+"insert",spiderDataArticle);
    }

    @Override
    public SpiderDataArticle getBySchoolIdAndUrl(int schoolId, String url,int categoryId) {
        Map<String,Object> map = new HashMap<>();
        map.put("schoolId",schoolId);
        map.put("url",url);
        map.put("categoryId",categoryId);
        return sst.selectOne(namespace + "getBySchoolIdAndUrl",map);
    }
}
