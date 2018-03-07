package com.webmagic.web.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 处理图片
 * Created by 庆文 on 2017/5/23 0023
 */
public class pictrueDealTest implements PageProcessor {


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
    }

    public static void main(String[] args) {
       Spider.create(new pictrueDealTest()).addUrl("http://zhaosheng.njtech.edu.cn/newstudent/view/aid/867/tag/zskx").thread(1).run();
    }
}
