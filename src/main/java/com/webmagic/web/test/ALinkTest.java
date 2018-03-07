package com.webmagic.web.test;

import com.webmagic.web.constant.QNConstant;
import com.webmagic.web.util.QNUtil;
import com.webmagic.web.util.SpiderUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.util.List;

/**
 * img base64
 * Created by 庆文 on 2017/5/23 0023
 */
public class ALinkTest implements PageProcessor {


    public static final String URL_LIST = "http://yz.chsi.com.cn/sch/?start=0";

    public static final String URL_POST_ONE = "http://yz\\.chsi\\.com\\.cn/sch/schoolInfo--schId-\\d*.dhtml";

    public static final String URL_POST = "http://yz\\.chsi\\.com\\.cn/sch/schoolInfo--schId-\\d*,categoryId-\\d*,mindex-6.dhtml";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    private int cont = 0;


    @Override
    public void process(Page page) {
        dealPage(page);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void dealPage(Page page) {
        List<String> list = page.getHtml().xpath("//div[@class='list_page_txt']").all();
        StringBuffer sb = new StringBuffer();
        for(String cu : list){
            sb.append(cu);
        }
        //处理内容
        String content = sb.toString();
        Document doc = Jsoup.parse(content);
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
        }
        System.out.println(doc.toString());
    }

    public static void main(String[] args) {
       Spider.create(new ALinkTest()).addUrl("http://zjczs.ccit.js.cn/Case.aspx?Id=1032").thread(1).run();
    }
}
