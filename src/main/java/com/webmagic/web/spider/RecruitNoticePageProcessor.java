package com.webmagic.web.spider;

import com.webmagic.web.constant.QNConstant;
import com.webmagic.web.model.SpiderDataAlarm;
import com.webmagic.web.model.SpiderDataArticle;
import com.webmagic.web.model.vo.SpiderDataConfigVO;
import com.webmagic.web.service.SpiderDaraArticleService;
import com.webmagic.web.util.DataFormatUtil;
import com.webmagic.web.util.QNUtil;
import com.webmagic.web.util.SpiderUtils;
import com.webmagic.web.util.SpringUtils;
import jodd.util.StringUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 爬虫逻辑的实现（针对文章内页）
 * create by sqw
 */
public class RecruitNoticePageProcessor implements PageProcessor {

    private SpiderDaraArticleService spiderDaraService = SpringUtils.getBean("spiderDataArticleServiceImpl");

    public SpiderDataConfigVO spiderDataConfigVO;

    public RecruitNoticePageProcessor(SpiderDataConfigVO spiderDataConfigVO) {
        this.spiderDataConfigVO = spiderDataConfigVO;
    }

    public static List<Integer> schoolIdList = new ArrayList<>();

    //对站点本身的一些配置信息，例如编码、HTTP头、超时时间、重试策略等、代理等，都可以通过设置Site对象来进行配置。
    public Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    public List<String> rightUrl = new ArrayList<>();

    @Override
    public void process(Page page) {
        List<SpiderDataAlarm> errorMessagesList = new ArrayList<>();
        //判断列表页，解析页面页的文章页链接加入的url地址中。
        //情况一处理特定规则的文章；情况二处理所有类别的文章；
        //下面用第一种情况测试
        //匹配所在文章列表
        if(page.getUrl().toString().equals(spiderDataConfigVO.getPageUrl().trim())){
            page.addTargetRequests(page.getHtml().links().regex(spiderDataConfigVO.getRuleListPageUrl()).all());
        }else{
            SpiderDataArticle model = new SpiderDataArticle();
            int schoolId = spiderDataConfigVO.getSchoolId();
            int categoryId = spiderDataConfigVO.getCategoryId();
            model.setSchoolId(schoolId);
            model.setCategoryId(categoryId);
            String timeRule = spiderDataConfigVO.getRuleTime();
            if(StringUtil.isEmpty(timeRule)){
                Calendar now = Calendar.getInstance();
                model.setPublishTime(now.getTime());
            }else {
                model.setPublishTime(DataFormatUtil.getData(page.getHtml().xpath(spiderDataConfigVO.getRuleTime()).get()));
            }
            model.setTitle(page.getHtml().xpath(spiderDataConfigVO.getRuleTitle()).get());
            model.setUrl(page.getUrl().toString());
            //处理特殊文本
            List<String> list = page.getHtml().xpath(spiderDataConfigVO.getRuleContent()).all();
            StringBuffer sb = new StringBuffer();
            for(String cu : list){
                sb.append(cu);
            }
            //处理内容
            String content = sb.toString();
            String domain = spiderDataConfigVO.getDomian();
            //获取当前页的域名
            try {
                URL url = new URL(page.getUrl().toString());
                domain = url.getProtocol()+"://"+url.getHost();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            //处理附件
            Document doc = Jsoup.parse(content);
            Elements aLinks = doc.select("a");
            for (Element a : aLinks) {
                a.removeAttr("style");
                a.removeAttr("class");
                String linkHref = a.attr("href");
                if(StringUtils.isBlank(linkHref)){
                    continue;
                }
                if(!linkHref.contains("http://")){
                    a.attr("href",domain+linkHref);
                }
            }
            //处理图片
            Elements imgLinks = doc.select("img");
            for(Element img : imgLinks){
                img.removeAttr("style");
                img.removeAttr("class");
                String linkSrc = img.attr("src");
                if(StringUtils.isBlank(linkSrc)){
                    continue;
                }
                //如果是base64编码
                if(SpiderUtils.checkBases64(linkSrc)){
                    File imageFile = SpiderUtils.decodeBase64ToImage(linkSrc);
                    String imagePath = QNUtil.upload(imageFile, QNConstant.VSCHOOLIMG_URL,QNConstant.VSCHOOLIMG_BUCKET,"spider/school/news/");
                    img.attr("src",imagePath);
                    continue;
                }

                if(!linkSrc.contains("http://")){
                    String imgPath = SpiderUtils.uploadImg(domain+linkSrc);
                    if(StringUtils.isBlank(imgPath)){
                        imgPath = domain+linkSrc;
                    }
                    img.attr("src",imgPath);
                }
            }
            //span
            Elements spanLinks = doc.select("span");
            for (Element span : spanLinks) {
                span.removeAttr("style");
                span.removeAttr("class");
            }
            //p
            Elements pLinks = doc.select("p");
            for (Element p : pLinks) {
                p.removeAttr("style");
                p.removeAttr("class");
            }
            //div
            Elements divLinks = doc.select("div");
            for (Element div : divLinks) {
                div.removeAttr("style");
                div.removeAttr("class");
            }
            //table
            Elements tableLinks = doc.select("table");
            for (Element table : tableLinks) {
                table.removeAttr("style");
                table.removeAttr("class");
            }
            //tbody
            Elements tbodyLinks = doc.select("tbody");
            for (Element tbody : tbodyLinks) {
                tbody.removeAttr("style");
                tbody.removeAttr("class");
            }
            //tr
            Elements trLinks = doc.select("tr");
            for (Element tr : trLinks) {
                tr.removeAttr("style");
                tr.removeAttr("class");
            }
            //td
            Elements tdLinks = doc.select("td");
            for (Element td : tdLinks) {
                td.removeAttr("style");
                td.removeAttr("class");
            }
            //h1
            Elements h1Links = doc.select("h1");
            for (Element h1 : h1Links) {
                h1.removeAttr("style");
                h1.removeAttr("class");
            }
            //strong
            Elements strongLinks = doc.select("strong");
            for (Element strong : strongLinks) {
                strong.removeAttr("style");
                strong.removeAttr("class");
            }
            //style
            Elements styleLinks = doc.select("style");
            styleLinks.remove();
            //script
            Elements scriptLinks = doc.select("script");
            scriptLinks.remove();
            model.setContent(doc.body().html());
            //处理表格
            Map<String, Object> result = SpiderUtils.filterContent(content);
            if (StringUtils.isNotBlank(MapUtils.getString(result,"msg"))) {
                model.setCaptureDesc(MapUtils.getString(result,"msg"));
                SpiderDataAlarm spiderErrorMessage = new SpiderDataAlarm();
                spiderErrorMessage.setUrl(page.getUrl().toString());
                spiderErrorMessage.setSchoolId(schoolId);
                spiderErrorMessage.setCategoryId(categoryId);
                spiderErrorMessage.setDec(MapUtils.getString(result,"msg"));
                errorMessagesList.add(spiderErrorMessage);
                spiderDaraService.insertDataAlarm(spiderErrorMessage);
            }
            page.getResultItems().put("repo", model);
            int schoolIdInsert = spiderDaraService.insertArticle(model);
            page.getResultItems().put("schools", schoolIdInsert);
        }
        //将错误信息保存待处理
        page.getResultItems().put("error", errorMessagesList);
    }


    @Override
    public Site getSite() {
        return site;
    }
}
