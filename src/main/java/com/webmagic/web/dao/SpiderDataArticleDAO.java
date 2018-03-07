package com.webmagic.web.dao;


import com.webmagic.web.model.SpiderDataArticle;

/**
 * Created by sqw on 2017/7/24.
 */
public interface SpiderDataArticleDAO {
    /**
     * 插入爬虫获取到的文章
     * @param spiderDataArticle
     * @return
     */
    public int insert(SpiderDataArticle spiderDataArticle);

    /**
     * 根据学校id和url查询
     * @param schoolId
     * @param url
     * @return
     */
    SpiderDataArticle getBySchoolIdAndUrl(int schoolId, String url,int categoryId);
}
