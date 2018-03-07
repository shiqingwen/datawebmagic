package com.webmagic.web.dao;

import com.webmagic.web.model.Document;

/**
 * @author shiqingwen
 * @Date 2017/11/24 10:38
 */
public interface DocumentDAO {
    /**
     * 文本插入
     * @param document
     * @return
     */
    int insert(Document document);
}
