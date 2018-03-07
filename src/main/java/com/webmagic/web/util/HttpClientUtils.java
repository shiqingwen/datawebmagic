package com.webmagic.web.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.util.Map;

public class HttpClientUtils {

	private static final Logger logger = Logger
			.getLogger(HttpClientUtils.class);
	private static HttpClient httpClient;
	private static HttpClient jsonHttpClient;
	static {
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(10000);
		connectionManager.getParams().setSoTimeout(10000);
		connectionManager.getParams().setMaxTotalConnections(1000);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(200);
		httpClient = new HttpClient(connectionManager);
		jsonHttpClient = new HttpClient(connectionManager);
	}

	public static String executePostHttpClientUTF(String url,
			Map<String, String> contentMap) {
		PostMethod method = new PostMethod(url);
		try {
			NameValuePair[] parameters = new NameValuePair[contentMap.size()];
			int i = 0;
			for (String key : contentMap.keySet()) {
				parameters[i] = new NameValuePair();
				parameters[i].setName(key);
				parameters[i].setValue(contentMap.get(key));
				i++;
			}
			method.addParameters(parameters);
			method.getParams().setContentCharset("utf-8");
			httpClient.executeMethod(method);
			return IOUtils.toString(method.getResponseBodyAsStream(), "utf-8");
		} catch (Exception e) {
			logger.error("execute  httpClient failed", e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	public static String executePostHttpClientGBK(String url,
			Map<String, String> contentMap) {
		PostMethod method = new PostMethod(url);
		try {
			NameValuePair[] parameters = new NameValuePair[contentMap.size()];
			int i = 0;
			for (String key : contentMap.keySet()) {
				parameters[i] = new NameValuePair();
				parameters[i].setName(key);
				parameters[i].setValue(contentMap.get(key));
				i++;
			}
			method.addParameters(parameters);
			method.getParams().setContentCharset("utf-8");
			httpClient.executeMethod(method);
			return IOUtils.toString(method.getResponseBodyAsStream(), "gbk");
		} catch (Exception e) {
			logger.error("execute  httpClient failed", e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	public static String executeJsonHttpClientUTF(String url, String jsonData) {
		PostMethod method = new PostMethod(url);
		try {
			StringRequestEntity requestEntity = new StringRequestEntity(
					jsonData, "application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
			jsonHttpClient.executeMethod(method);
			return IOUtils.toString(method.getResponseBodyAsStream(), "utf-8");
		} catch (Exception e) {
			logger.error("execute  httpClient failed", e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	/**
	 * post方式发送xml字符串，并获取得到xml字符串
	 * 在这里进行编码
	 * @param url
	 * @param xmlStr
	 * @return
	 */
	public static String executePostXMLUTF(String url, String xmlStr) {
		PostMethod post  = new PostMethod(url);  
        String info = null;  
        try {  
        	//在这里已经对字符串流进行了编码，在这里编码就可以了
        	StringRequestEntity entity = new StringRequestEntity(xmlStr, "text/xml",  
            "utf-8");  
            post.setRequestEntity(entity);  
            httpClient.executeMethod(post);   
            int code = post.getStatusCode();  
            if (code == HttpStatus.SC_OK) {
            	info = IOUtils.toString(post.getResponseBodyAsStream(), "utf-8");
            }
        } catch (Exception ex) { 
        	logger.error("post xml erro",ex);
            ex.printStackTrace();  
        } finally {  
            post.releaseConnection();  
        }  
        return info;
	}

	public static String executePostHttpClient(String url,
			Map<String, String> contentMap, String charset) {
		PostMethod method = new PostMethod(url);
		try {
			NameValuePair[] parameters = new NameValuePair[contentMap.size()];
			int i = 0;
			for (String key : contentMap.keySet()) {
				parameters[i] = new NameValuePair();
				parameters[i].setName(key);
				parameters[i].setValue(contentMap.get(key));
				i++;
			}
			method.addParameters(parameters);
			method.getParams().setContentCharset(charset);
			httpClient.executeMethod(method);
			return IOUtils.toString(method.getResponseBodyAsStream(), charset);
		} catch (Exception e) {
			logger.error("execute  httpClient failed", e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	public static String executeGetHttpClient(String url,
			Map<String, String> contentMap) {
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		for (String key : contentMap.keySet()) {
			if (i == 0) {
				buffer.append("?").append(key).append("=")
						.append(contentMap.get(key));
			} else {
				buffer.append("&").append(key).append("=")
						.append(contentMap.get(key));
			}
			i++;
		}
		GetMethod method = new GetMethod(url + buffer.toString());
		try {
			httpClient.executeMethod(method);
			return IOUtils.toString(method.getResponseBodyAsStream(), "utf-8");
		} catch (Exception e) {
			logger.error("execute  httpClient failed", e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	public static int executeGetHttpClient(String url) {
		GetMethod method = new GetMethod(url);
		try {
			return httpClient.executeMethod(method);
		} catch (Exception e) {
			logger.error("execute httpClient failed!url:"+url, e);
		} finally {
			method.releaseConnection();
		}
		return 0;
	}

	public static Header[] executeGetHttpClientHeaders(String url,
			Map<String, String> contentMap) {
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		for (String key : contentMap.keySet()) {
			if (i == 0) {
				buffer.append("?").append(key).append("=")
						.append(contentMap.get(key));
			} else {
				buffer.append("&").append(key).append("=")
						.append(contentMap.get(key));
			}
			i++;
		}
		GetMethod method = new GetMethod(url + buffer.toString());
		try {
			httpClient.executeMethod(method);
			return (Header[]) method.getResponseHeaders();
		} catch (Exception e) {
			logger.error("execute  httpClient failed", e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

}
