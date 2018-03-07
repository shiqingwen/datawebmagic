package com.webmagic.web.spider;
import com.webmagic.web.model.SpiderDataArticle;
import com.webmagic.web.model.SpiderDataAlarm;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yht on 2017/7/16.
 */
public class RecruitNoticePipeline implements Pipeline {
    private static Logger logger  = Logger.getLogger(RecruitNoticePipeline.class);

    public static List<SpiderDataAlarm> errorList = new ArrayList<>();
    public static List<SpiderDataArticle> articleList = new ArrayList<>();
    public static List<Integer> schoolIdList = new ArrayList<>();

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            SpiderDataArticle spiderDataArticle = resultItems.get("repo");
            List<SpiderDataAlarm> spiderErrorMessages = resultItems.get("error");
            errorList.addAll(spiderErrorMessages);
            if(spiderDataArticle!=null){
                articleList.add(spiderDataArticle);
            }
            if(resultItems.get("schools") != null){
                int schoolId = resultItems.get("schools");
                if(schoolId != 0){
                    if(!schoolIdList.contains(schoolId)){
                        schoolIdList.add(schoolId);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("处理抓取数据出错",e);
        }

    }
}
