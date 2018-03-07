package com.webmagic.web.service;

import com.webmagic.web.model.SpiderDataArticle;

/**
 * 文章
 * @author shiqingwen
 * @Date 2017/11/24 10:55
 */
public interface DocumentService {
    /**
     *  插入文章
     * @param model
     * @return
     */
    int insertInfo(SpiderDataArticle model);
}
