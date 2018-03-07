package com.webmagic.web.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期格式的格式化
 * Created by sqw on 2017/7/21.
 */
public class DataFormatUtil {
    private static Logger logger = Logger.getLogger(DataFormatUtil.class);

    public static Date getData(String date){
        Pattern pattern = Pattern.compile("[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}");
        Pattern pattern1 = Pattern.compile("[0-9]{4}[年][0-9]{1,2}[月][0-9]{1,2}[日]");
        Pattern pattern2 = Pattern.compile("[0-9]{4}[/][0-9]{1,2}[/][0-9]{1,2}");
        Matcher matcher = pattern.matcher(date);
        Matcher matcher1= pattern1.matcher(date);
        Matcher matcher2= pattern2.matcher(date);
        String dateStr = null;
        String str = null;
        if(matcher.find()){
            dateStr = matcher.group(0);
            str =dateStr.toString();

        }
        if(matcher1.find()){
            dateStr = matcher1.group(0);
            str =dateStr.toString();
            str = str.replace("年","-");
            str = str.replace("月","-");
            str = str.replace("日","");
        }
        if(matcher2.find()){
            dateStr = matcher2.group(0);
            str =dateStr.toString();
            str = str.replace("/","-");

        }
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(str);
            return date1;
        } catch (ParseException e) {
            logger.error("格式化时间异常",e);
        }

        return null;
    }
}
