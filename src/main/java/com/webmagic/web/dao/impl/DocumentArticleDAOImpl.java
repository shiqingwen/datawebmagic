package com.webmagic.web.dao.impl;

import com.webmagic.web.dao.DocumentArticleDAO;
import com.webmagic.web.model.DocumentArticle;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author shiqingwen
 * @Date 2017/11/24 10:50
 */
@Repository
public class DocumentArticleDAOImpl implements DocumentArticleDAO {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sst;

    private final String namespace="T.DocumentArticle.";

    @Override
    public int insert(DocumentArticle documentArticle) {
        return sst.insert(namespace + "insert",documentArticle);
    }
}
