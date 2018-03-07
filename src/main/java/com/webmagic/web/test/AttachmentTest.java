package com.webmagic.web.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理附件
 * Created by 庆文 on 2017/5/23 0023
 */
public class AttachmentTest implements PageProcessor {


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
        String count = page.getHtml().xpath("//div[@class='content']/html()").get().replaceAll("style=\"(.+?)\"","");
        /*Pattern pattern = Pattern.compile("<a\\s+href=\"(.+?)\"\\s+title=\"(.+?)\">");
        Matcher matcher = pattern.matcher(count);
        while (matcher.find()) {
            count =  count.replace(matcher.group(1),"http://zhaosheng.njtech.edu.cn/"+matcher.group(1));
        }*/
        Document doc = Jsoup.parse(count);
        Elements pngs = doc.select("a");
        for (Element img : pngs) {
            String linkSrc = img.attr("href");
            count =  count.replace(linkSrc,"http://zhaosheng.njtech.edu.cn/"+linkSrc);
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
       Spider.create(new AttachmentTest()).addUrl("http://zhaosheng.njtech.edu.cn/newstudent/view/aid/879/tag/zskx").thread(1).run();
    }
}
