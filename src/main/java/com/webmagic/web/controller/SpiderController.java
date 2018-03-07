package com.webmagic.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.aliyun.common.utils.IOUtils;
import com.webmagic.web.model.Result;
import com.webmagic.web.model.SpiderDataAlarm;
import com.webmagic.web.model.vo.SchoolVO;
import com.webmagic.web.model.vo.SpiderDataConfigVO;
import com.webmagic.web.service.SpiderDaraArticleService;
import com.webmagic.web.service.SpiderDataConfigService;
import com.webmagic.web.spider.RecruitNoticePageProcessor;
import com.webmagic.web.spider.RecruitNoticePipeline;
import com.webmagic.web.util.DesUtil;
import com.webmagic.web.util.HttpClientUtils;
import com.webmagic.web.util.MD5Signature;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬虫调度程序
 * Created by sqw on 2017/7/19.
 */

@Controller
@RequestMapping("/spider")
public class SpiderController {

    private static Logger logger = Logger.getLogger(SpiderController.class);

    @Resource
    private SpiderDataConfigService spiderDataConfigService;

    @Resource
    private SpiderDaraArticleService spiderDaraArticleService;


    /**
     * 报警短信deskey
     */
    private static final String ALARM_SMS_DESKEY = "TCjkKjtN";

    /**
     * 报警短信md5 sign
     */
    private static final String ALARM_SMS_MD5_SIGN = "BbpRqNfmigO88f2OlxLQ";

    @RequestMapping("/start")
    @ResponseBody
    public Result startSpider(HttpServletRequest request) {
       /* Result result = getParamMap(ALARM_SMS_DESKEY, ALARM_SMS_MD5_SIGN, request);
        if(!result.isSuccess()){
            return result;
        }*/
        List<SpiderDataConfigVO> spiderDataConfigVOList = spiderDataConfigService.queryAllSpiderConfig();
        //Export export = new Export();
        RecruitNoticePipeline.articleList.clear();
        RecruitNoticePipeline.errorList.clear();
        RecruitNoticePipeline.schoolIdList.clear();
        List<SpiderDataAlarm> errorList = new ArrayList<>();
        for (SpiderDataConfigVO spiderDataConfigVO : spiderDataConfigVOList) {
            String pageUrl = spiderDataConfigVO.getPageUrl();
            int httpStatus = HttpClientUtils.executeGetHttpClient(pageUrl);
            //访问不通报警
            if(httpStatus != 200){
                SpiderDataAlarm errorMessage  = new SpiderDataAlarm();
                errorMessage.setSchoolId(spiderDataConfigVO.getSchoolId());
                errorMessage.setUrl(pageUrl);
                errorMessage.setDec("网址访问不通");
                errorList.add(errorMessage);
                continue;
            }
            //爬取所需信息
            Spider.create(new RecruitNoticePageProcessor(spiderDataConfigVO))
                    .addUrl(spiderDataConfigVO.getPageUrl())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(new RecruitNoticePipeline())
                    .thread(1).setExitWhenComplete(true).run();
        }
        logger.info("发送警报开始===========================================");
        List<Integer> list = RecruitNoticePipeline.schoolIdList;
        StringBuilder sb = new StringBuilder();
        for(Integer schoolId : list){
            if(schoolId == 0){
                continue;
            }
            SchoolVO schoolVO = spiderDataConfigService.getBySchoolId(schoolId);
            if(schoolVO == null){
                continue;
            }
            sb.append(schoolVO.getSchoolName()).append(",");
        }
        //发送更新报警
        if(StringUtils.isNotBlank(sb.toString())){
            logger.info(sb.toString()+"资讯有更新");
            //sendMonitorAlarmInfo(sb.toString()+"资讯有更新");
        }
        logger.info("发送警报结束===========================================");
        return new Result(true,"成功");
    }

    /**
     * 发送报警信息
     */
    private void sendMonitorAlarmInfo(String info) {
        //组织参数
        Map<String, Object> dingMsg = new HashMap<>();
        //消息类型
        dingMsg.put("msgtype", "text");
        //消息内容
        Map<String, String> text = new HashMap<>();
        text.put("content", info);
        dingMsg.put("text", text);
        //被@人的手机号
        Map<String, JSONArray> at = new HashMap<>();
        at.put("atMobiles", JSON.parseArray("[]"));
        dingMsg.put("at", at);
        //@所有人时:true,否则为:false
        dingMsg.put("isAtAll", false);
        //钉钉机器人发送消息地址
        String dingSendUrl = "https://oapi.dingtalk.com/robot/send?access_token=948620cf59859456978754b56aec521bc77673e45a70c6ed8ce5b078609e7101";
        HttpClientUtils.executeJsonHttpClientUTF(dingSendUrl, JSON.toJSONString(dingMsg));
    }


    private Result getParamMap(String desKey, String md5Sign, HttpServletRequest request) {
        try {
            String streamContent = IOUtils.readStreamAsString(request.getInputStream(), "utf-8");
            logger.info("解码前streamContent:" + streamContent);
            if(StringUtils.isBlank(streamContent)){
                return new Result(false, 1002, "非法的访问形式");
            }
            String[] signs = StringUtils.split(streamContent, "-");
            if (signs.length < 2) {
                logger.info("缺少签名！");
                return new Result(false, 1003, "接口参数缺少签名");
            }
            String params = signs[0];
            String md5SignStr = signs[1];
            //验证md5签名
            if (!MD5Signature.verify(params, md5SignStr, md5Sign)) {
                logger.info("签名不正确！");
                return new Result(false, 1004, "验签失败");
            }
            //des解密数据
            params = DesUtil.decodeValue(params, desKey);
            params = URLDecoder.decode(params, "UTF-8");
            logger.info("解码后streamContent:" + params);
            return new Result(true, 1, "ok", JSON.parseObject(params, Map.class));
        } catch (Exception e) {
            logger.error(e);
            return new Result(false, 9999, "程序异常");
        }
    }
}

