package com.webmagic.web.dao.impl;

import com.webmagic.web.dao.DocumentDAO;
import com.webmagic.web.model.Document;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author shiqingwen
 * @Date 2017/11/24 10:38
 */
@Repository
public class DocumentDAOImpl implements DocumentDAO {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sst;

    private final String namespace="T.Document.";

    @Override
    public int insert(Document document) {
        return sst.insert(namespace + "insert",document);
    }
}
