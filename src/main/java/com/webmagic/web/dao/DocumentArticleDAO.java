package com.webmagic.web.dao;

import com.webmagic.web.model.DocumentArticle;

/**
 *  文章详情
 * @author shiqingwen
 * @Date 2017/11/24 10:49
 */
public interface DocumentArticleDAO {
    /**
     * 插入文章详情
     * @param documentArticle
     * @return
     */
    int insert(DocumentArticle documentArticle);
}
