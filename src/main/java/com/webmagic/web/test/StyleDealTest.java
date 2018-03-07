package com.webmagic.web.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * 特殊样式处理
 * Created by 庆文 on 2017/5/23 0023
 */
public class StyleDealTest implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);


    @Override
    public void process(Page page) {
        dealPage(page);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void dealPage(Page page) {
        List<String> list = page.getHtml().xpath("//p[@class='MsoNormal']/html()").all();
        StringBuffer sb = new StringBuffer();
        for(String cu : list){
            sb.append(cu);
        }
        String content = sb.toString();
        content = content.replaceAll("style=\"(.+?)\"","").replaceAll("class=\"(.+?)\"","");
        Document doc = Jsoup.parse(content);
        System.out.println(doc.toString());
    }

    public static void main(String[] args) {
       Spider.create(new StyleDealTest()).addUrl("http://www.ao.fudan.edu.cn/index!list.html?sideNav=302&ccid=9301&topNav=282").thread(1).run();
    }
}
