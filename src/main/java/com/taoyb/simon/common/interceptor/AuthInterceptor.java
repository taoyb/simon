package com.taoyb.simon.common.interceptor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taoyb.simon.common.utils.JudgeUtils;
import com.taoyb.simon.web.model.TybUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by taoyb on 2017-04-15.
 * 对登录状态进行拦截
 */

public class AuthInterceptor extends HandlerInterceptorAdapter {
    /* @Autowired
     private Config config;*/
    private static final String[] IGNORE_URI = {"/", "findHeadMenu", "login", "loginOut", "connectWeixin"};

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*String requestUrl = request.getRequestURL().toString();
        String[] sd = requestUrl.split("/");
        String subUrl = sd.length > 5 ? "/" : sd.length == 5 ? sd[4] : sd.length == 3 ? "/" : "";
        String referer = request.getHeader("referer");
        //String a = request.getContextPath();
        try {
            TybUser user = (TybUser) request.getSession().getAttribute("loginUser");
            if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null) {
                *//*String applicationName = "/" + request.getContextPath().split("/")[request.getContextPath().split("/").length - 1] + "/";
                if (requestUrl.endsWith(applicationName)) {
                    return true;
                }*//*
                boolean flag = false;
                for (String s : IGNORE_URI) {
                    if (subUrl.indexOf(s) == 0) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {

                    String jsonStr = "{\"statusCode\":301,\"message\":\"登录已超时，请重新登录！\"}";
                    if (null == user || user.getUserId() == null) {
                        // 未登录
                        ObjectMapper mapper = new ObjectMapper();//用org.json.jar将字符串转为json
                        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
                        JsonNode df = mapper.readValue(jsonStr, JsonNode.class);
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json; charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.print(df);
                        out.flush();
                        out.close();
                        return false;
                    } else {
                        return true;
                    }
                }
                return true;
            }
            //微信接入需注释这一段
            else if (StringUtils.isEmpty(referer)) {
                for (String s : IGNORE_URI) {
                    if (subUrl.indexOf(s) == 0) {
                        return true;
                    }
                }
                *//*if (StringUtils.isEmpty(JudgeUtils.getStrUrl(requestUrl))) {
                    request.getRequestDispatcher("/login/loginOut").forward(request, response);
                }*//*
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }*/
        return true;
    }
}
