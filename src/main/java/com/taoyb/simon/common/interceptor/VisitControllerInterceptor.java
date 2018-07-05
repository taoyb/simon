package com.taoyb.simon.common.interceptor;
import com.taoyb.simon.common.utils.JudgeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by taoyb on 2016-12-07.
 * 访问控制器（由web.xml配置）
 */
public class VisitControllerInterceptor extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getServletPath().endsWith(JudgeUtils.getStrUrl(request.getServletPath()))||JudgeUtils.getStrUrl(request.getServletPath()).equals("")){
            logger.info("当前访问控制器地址为："+request.getRequestURI());
        }
        filterChain.doFilter(request, response);
    }
}
