package com.webmagic.web.service;


import com.webmagic.web.model.SpiderDataAlarm;
import com.webmagic.web.model.SpiderDataArticle;

/**
 * Created by sqw on 2017/7/24.
 */
public interface SpiderDaraArticleService {

    /**
     *  插入文章
     * @param spiderErrorMessage
     * @return
     */
    public int insertDataAlarm(SpiderDataAlarm spiderErrorMessage);

    /**
     * 插入文章
     * @param model
     * @return
     */
    int insertArticle(SpiderDataArticle model);
}
