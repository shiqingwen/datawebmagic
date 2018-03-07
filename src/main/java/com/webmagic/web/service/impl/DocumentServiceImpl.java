package com.webmagic.web.service.impl;

import com.webmagic.web.dao.DocumentArticleDAO;
import com.webmagic.web.dao.DocumentDAO;
import com.webmagic.web.model.Document;
import com.webmagic.web.model.DocumentArticle;
import com.webmagic.web.model.SpiderDataArticle;
import com.webmagic.web.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author shiqingwen
 * @Date 2017/11/24 10:55
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Resource
    private DocumentDAO documentDAO;

    @Resource
    private DocumentArticleDAO documentArticleDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public int insertInfo(SpiderDataArticle model) {
        Document document = new Document();
        document.setCategoryId(model.getCategoryId());
        document.setCreateTime(model.getPublishTime().getTime()/1000);
        document.setSchoolId(model.getSchoolId());
        document.setTitle(model.getTitle());
        document.setCaptureDesc(model.getCaptureDesc());
        documentDAO.insert(document);
        DocumentArticle documentArticle = new DocumentArticle();
        documentArticle.setContent(model.getContent());
        documentArticle.setId(document.getId());
        documentArticleDAO.insert(documentArticle);
        return document.getId();
    }

}
