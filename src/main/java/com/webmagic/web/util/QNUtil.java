package com.webmagic.web.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.webmagic.web.constant.QNConstant;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * 七牛上传工具类
 * @author Xingyf 2017.2.28
 */
public class QNUtil {

    private static Logger logger = Logger.getLogger(QNUtil.class);

    /**
     * 上传到七牛
     * @param file 文件
     * @param url   七牛地址
     * @param bucket 七牛bucket
     * @return
     */
    public static String upload(File file,String url, String bucket,String prefix){
        if(file == null || file.isDirectory() || !file.exists()){
            return "";
        }
        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
        String fileName = prefix + UUID.randomUUID().toString().replace("-","") + suffix;
        try {
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone0());
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(QNConstant.AK, QNConstant.SK);
            //创建上传对象
            String upToken = auth.uploadToken(bucket);
            //调用put方法上传
            Response res = uploadManager.put(file, fileName, upToken);
            if(res.statusCode != 200){
                return "";
            }
        } catch (QiniuException e) {
            logger.error("QN upload error!", e);
            return "";
        }finally {
            if(file.isFile() && file.exists()){
                file.delete();
            }
        }
        return url + "/" + fileName;
    }

    /**
     * 删除七牛空间文件
     * @param fileName
     */
    public static void delete(String fileName,String bucket){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        try {
            Auth auth = Auth.create(QNConstant.AK, QNConstant.SK);
            BucketManager bucketManager = new BucketManager(auth, cfg);
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException e) {
            logger.error("QN delete error!", e);
        }
    }

    /**
     * 保存网络图片到本地
     * @param imgurl
     * @return
     */
    public static File readHttpImg(String imgurl){
        String path = PathKit.getWebRootPath() + File.separator + "tempimg";
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }

        File imageFile = new File(path + File.separator + imgurl.substring(imgurl.lastIndexOf("/")+1));
        try {
            //new一个URL对象
            URL url = new URL(imgurl);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
        } catch (Exception e) {
            if(imageFile.exists()){
                imageFile.delete();
            }
            logger.error(e);
        }
        return imageFile;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
