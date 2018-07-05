package com.taoyb.simon.web.utils;

import com.taoyb.simon.web.model.TybUser;
import com.taoyb.simon.web.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by taoyb on 2016-12-08.
 * 将注入的service封装
 */
public class BaseController {
    public HttpServletRequest request;
    public HttpServletResponse response;
    /**
     * spring 中request、response是线程安全的，可以直接注入
     *
     * @ModelAttribute注解只有在被
     * @Controller和@ControllerAdvice两个注解的类下使用 ModelAttribute的作用
     *    1)放置在方法的形参上： 表示引用Model中的数据
     *    2)放置在方法上面：表示请求该类的每个Action前都会首先执行它也可以将一些准备数据的操作放置在该方法里面。
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    @Autowired
    public TybMenusService tybMenusService;
    @Autowired
    public TybUserService tybUserService;
    /*
    @Autowired
    public ICacheService cacheService;

    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public JsonResult expHandler(HttpServletRequest request, Exception ex) {
        if (ex instanceof MyException) {
            return new JsonResult((MyException) ex);
        } else {
            ex.printStackTrace();
            ex.printStackTrace();
            return new JsonResult(new MyException("000001", ex.getMessage()));
        }
    }*/
    @Autowired
    public TybTestService tybTestService;
    /*@Autowired
    public Config config;*/

    public Integer getPageNum(){
        String pageNum = request.getParameter("pageNum");
        return Integer.parseInt(StringUtils.isEmpty(pageNum)?"1":pageNum);
    }
    public Integer getPageSize(){
        String pageSize = request.getParameter("pageSize");
        return Integer.parseInt(pageSize==null?"20":pageSize);
    }
    @Autowired
    public TybRecordService tybRecordService;
    @Autowired
    public TybLeaveService tybLeaveService;

    public TybUser getLoginUser(){
        Object obj = request.getSession().getAttribute("loginUser");
        if (obj != null) return (TybUser)obj;
        else return new TybUser();
    }
}
