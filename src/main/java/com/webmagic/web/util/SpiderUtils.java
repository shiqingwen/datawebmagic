package com.webmagic.web.util;

import com.webmagic.web.constant.QNConstant;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 爬取工具类
 * @author shiqingwen
 * @Date 2017/11/23 17:02
 */
public class SpiderUtils {

    /**
     * 上传图片
     * @param fullPath
     * @return
     */
    public static String uploadImg(String fullPath){
        File file = QNUtil.readHttpImg(fullPath);
        return QNUtil.upload(file, QNConstant.VSCHOOLIMG_URL,QNConstant.VSCHOOLIMG_BUCKET,"spider/school/news/");
    }

    /**
     * 处理特殊内容
     * @param content
     * @return
     */
    public static Map<String,Object> filterContent(String content){
        Map<String,Object> map = new HashedMap();
        map.put("msg","");
        if(content.contains("<table") || content.contains("<tbody")){
            map.put("msg","文章内页包含表格");
        }
        return map;
    }



    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64
     *            base64编码的图片信息
     * @return
     */
    public static File decodeBase64ToImage(String base64) {
        base64 = base64.replace("data:image/png;base64,","");
        String path = PathKit.getWebRootPath() + File.separator + "tempimg";
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        String filePath = path + File.separator + UUID.randomUUID().toString().replace("-","")+".png";
        File imageFile = new File(filePath);
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            FileOutputStream write = new FileOutputStream(imageFile);
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }



    /**
     * 解析时候是base64
     * @param param
     * @return
     */
    public static boolean checkBases64(String param){
        boolean flag = false;
        try{
            /*BASE64Decoder decoder = new BASE64Decoder();
            decoder.decodeBuffer(param);*/
            if(StringUtils.containsIgnoreCase(param,"base64")){
                flag = true;
            }
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }
}
