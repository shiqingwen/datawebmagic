package com.webmagic.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.webmagic.web.util.IpUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 全局拦截器 打点、判断登陆
 * @author Xingyf
 */
public class GlobalInterceptor implements HandlerInterceptor {

    private static Logger logger = Logger.getLogger(GlobalInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> params = request.getParameterMap();
        StringBuilder query = new StringBuilder();
        for(Map.Entry<String, String[]> p : params.entrySet()){
            query.append(p.getKey()+"="+ JSON.toJSONString(p.getValue())+"&");
        }
        logger.info("打点日志["+ DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss,S")+"]：URI："+request.getRequestURI()+
                ",QUERY:"+ StringUtils.removeEnd(query.toString(), "&")+",IP:"+ IpUtils.getRealIp(request));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
