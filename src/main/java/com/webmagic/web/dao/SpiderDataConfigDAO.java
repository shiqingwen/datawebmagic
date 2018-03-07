package com.webmagic.web.dao;


import com.webmagic.web.model.vo.SchoolVO;
import com.webmagic.web.model.vo.SpiderDataConfigVO;

import java.util.List;

/**
 * Created by cuidong on 2017/7/19 0019.
 * 爬虫配置
 */
public interface SpiderDataConfigDAO {
    /**
     * 查询所有的爬虫配置
     * @return
     */
    List<SpiderDataConfigVO> queryAllSpiderConfig();

    /**
     * 根据学校id查询学校信息
     * @param schoolId
     * @return
     */
    SchoolVO getBySchoolId(Integer schoolId);
}
