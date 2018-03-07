package com.webmagic.web.service.impl;

import com.webmagic.web.dao.SpiderDataAlarmDAO;
import com.webmagic.web.dao.SpiderDataArticleDAO;
import com.webmagic.web.db.DynamicDataSource;
import com.webmagic.web.model.SpiderDataAlarm;
import com.webmagic.web.model.SpiderDataArticle;
import com.webmagic.web.service.DocumentService;
import com.webmagic.web.service.SpiderDaraArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 爬虫service
 * @author sqw
 *
 */
@Service
public class SpiderDataArticleServiceImpl implements SpiderDaraArticleService {

    @Resource
    private SpiderDataArticleDAO spiderDataArticleDAO;

    @Resource
    private SpiderDataAlarmDAO spiderDataAlarmDAO;

    @Resource
    private DocumentService documentService;

    @Override
    public int insertDataAlarm(SpiderDataAlarm spiderDataAlarm) {
        int flag = 0;
        SpiderDataAlarm spiderDataAlarmCheck = spiderDataAlarmDAO.getByUrlAndSchoolId(spiderDataAlarm.getSchoolId(),spiderDataAlarm.getUrl());
        if(spiderDataAlarmCheck == null){
            flag = spiderDataAlarmDAO.insert(spiderDataAlarm);
        }else{
            flag = 1;
        }
        return flag;
    }

    @Override
    public int insertArticle(SpiderDataArticle model) {
        int schoolId = 0;
        SpiderDataArticle spiderDataArticle = spiderDataArticleDAO.getBySchoolIdAndUrl(model.getSchoolId(),model.getUrl(),model.getCategoryId());
        //爬取日志表
        if(spiderDataArticle == null){
            int flag = spiderDataArticleDAO.insert(model);
            if(flag == 1){
                DynamicDataSource.setCustomerType(DynamicDataSource.DATA_SOURCE_B);
                flag = documentService.insertInfo(model);
                DynamicDataSource.clearCustomerType();
            }
            schoolId = model.getSchoolId();
        }
        return schoolId;
    }
}
