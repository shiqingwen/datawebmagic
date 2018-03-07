package com.webmagic.web.service.impl;

import com.webmagic.web.dao.SpiderDataConfigDAO;
import com.webmagic.web.model.vo.SchoolVO;
import com.webmagic.web.model.vo.SpiderDataConfigVO;
import com.webmagic.web.service.SpiderDataConfigService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cuidong on 2017/7/19 0019.
 * 爬虫配置
 */
@Service
public class SpiderDataConfigServiceImpl implements SpiderDataConfigService {

    private Logger logger=Logger.getLogger(SpiderDataConfigServiceImpl.class);

    @Resource
    private SpiderDataConfigDAO spiderDataConfigDao;

    @Override
    public List<SpiderDataConfigVO> queryAllSpiderConfig() {
        return spiderDataConfigDao.queryAllSpiderConfig();
    }

    @Override
    public SchoolVO getBySchoolId(Integer schoolId) {
        return spiderDataConfigDao.getBySchoolId(schoolId);
    }
}
