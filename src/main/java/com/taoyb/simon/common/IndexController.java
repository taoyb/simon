package com.taoyb.simon.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by taoyb on 2016-12-07.
 * 登陆页设置
 */
@Controller
public class IndexController extends MultiActionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/blog/findHeadMenu";
    }
}
