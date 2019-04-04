package org.wz.bussiness.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;
import org.wz.config.bean.BufferedHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @project: userMaster
 * @package: org.wz.bussiness.filter
 * @author: Administrator
 * @crDate: 2019/3/25 14:44
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class RequestCachingFilter extends OncePerRequestFilter {

    private static Logger logger = LogManager.getLogger(RequestCachingFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;
        if (isFirstRequest && !(request instanceof BufferedHttpServletRequest)) {
            requestToUse = new BufferedHttpServletRequest(request, 1024);
        }
        try {
            filterChain.doFilter(requestToUse, response);
        } catch (Exception e) {
            logger.error("RequestCachingFilter>>>>>>>>>", e);
        } finally {
            this.printRequest(requestToUse);
            if (requestToUse instanceof BufferedHttpServletRequest) {
                ((BufferedHttpServletRequest) requestToUse).release();
            }
        }
    }

    private void printRequest(HttpServletRequest request) {
        String body = StringUtils.EMPTY;
        try {
            if (request instanceof BufferedHttpServletRequest) {
                body = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            logger.error("printRequest 获取body异常...", e);
        }

        JSONObject requestJ = new JSONObject();
        JSONObject headers = new JSONObject();
        Collections.list(request.getHeaderNames())
                .stream()
                .forEach(name -> headers.put(name, request.getHeader(name)));
        requestJ.put("headers", headers);
        requestJ.put("parameters", request.getParameterMap());
        requestJ.put("body", body);
        requestJ.put("remote-user", request.getRemoteUser());
        requestJ.put("remote-addr", request.getRemoteAddr());
        requestJ.put("remote-host", request.getRemoteHost());
        requestJ.put("remote-port", request.getRemotePort());
        requestJ.put("uri", request.getRequestURI());
        requestJ.put("url", request.getRequestURL());
        requestJ.put("servlet-path", request.getServletPath());
        requestJ.put("method", request.getMethod());
        requestJ.put("query", request.getQueryString());
        requestJ.put("path-info", request.getPathInfo());
        requestJ.put("context-path", request.getContextPath());

        logger.info("Request-Info: " + JSON.toJSONString(requestJ, SerializerFeature.PrettyFormat));
    }

}
