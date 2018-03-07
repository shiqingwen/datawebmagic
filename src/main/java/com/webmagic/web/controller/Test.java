package com.webmagic.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shiqingwen
 * @Date 2017/11/23 20:34
 */
public class Test {

    public static void main(String[] args) {
        String str = "http://zjb.ycit.cn/adminnews/news.asp?id=754&smallid=39";
        String regx = "http://zjb.ycit.cn/adminnews/news.asp[?]id=\\d+&smallid=\\d+";
        Pattern pattern = Pattern.compile(regx);
        Matcher macher = pattern.matcher(str);
        System.out.println(macher.matches());

    }
}
