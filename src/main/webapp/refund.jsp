<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.gk.platform.service.util.security.DesUtil" %>
<%@ page import="com.gk.platform.service.util.security.MD5Signature" %>
<%@ page import="com.gk.platform.service.util.http.HttpClientUtils" %>
<%
    //String url = "http://pay.360eol.com/pay/order/createOrder";
    String url = "http://pay.ngrok.freecoding.net.cn/pay/channel/refundOrder";
    Map<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("bizOrderId", "295000000");
    hashMap.put("amount", "0.01");
    hashMap.put("refundName", "不想买了");
    hashMap.put("bizId","1010");
    hashMap.put("userIp","192.168.0.12");
    hashMap.put("userId","121");
    String paramJson = JSON.toJSONString(hashMap);
    String desParam = "";
    String md5sign = "";
    try {
		paramJson = URLEncoder.encode(paramJson,"UTF-8");
        desParam = DesUtil.encode(paramJson, "py@&*abc");
        md5sign = MD5Signature.sign(desParam, "kji!@#$^*SDFGHJK&QINGWEN");
    } catch (Exception e) {
        e.printStackTrace();
    }
    paramJson = desParam + "-" + md5sign;
    String cback = HttpClientUtils.executeJsonHttpClientUTF(url, paramJson);
    System.out.println(cback);
%>
<%!

%>
