package com.webmagic.web.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 *  苏州大学
 * Created by 庆文 on 2017/5/23 0023
 */
public class SUZHOUTest implements PageProcessor {


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
        //String sss = page.getHtml().xpath("//table/tbody/tr[1]/td/span[@id='ctl00_ContentPlaceHolder1_View1_Label2']/b/text()").get();
        List<String> list = page.getHtml().links().regex("http://zsb.suda.edu.cn/view.aspx[?]id=\\d{4}").all();
        System.out.println(list);
    }

    public static void main(String[] args) {
       Spider.create(new SUZHOUTest()).addUrl("http://zsb.suda.edu.cn/zszc.aspx").thread(1).run();
    }
}
