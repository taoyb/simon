package com.taoyb.simon.web.controller;

import com.taoyb.simon.common.utils.Config;
import com.taoyb.simon.common.utils.JsonResult;
import com.taoyb.simon.common.utils.Md5Util;
import com.taoyb.simon.web.dto.LoginDto;
import com.taoyb.simon.web.model.TybUser;
import com.taoyb.simon.web.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-08.
 */

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private Config config;

    /**
     * @Author: TYB
     * @Date: 2017-01-03 下午 3:38
     * @Des: 登陆页面获取基础数据
     */

    @RequestMapping(value = "/preLogin")
    public @ResponseBody
    JsonResult preLogin() {
        LoginDto model = new LoginDto();
        model.setUserName(MyCookie.getCookie(Simon.COOKIE_USERNAME, request));
        model.setPassword(MyCookie.getCookie(Simon.COOKIE_PASSWORD, true, request));
        ////model.setRemberPwd(MyCookie.getCookie(Simon.COOKIE_REMBER_PWD, request));
        model.setTipMessage("");
        TybUser user = Tools.getUser();
        model.setSessionUserName(user == null ? null : user.getUserName());
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("model", model);
        return new JsonResult(returnMap);
    }

    /**
     * @Author: TYB
     * @Date: 2017-02-20 下午 2:26
     * @Des: 用户登录校验
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody AjaxDone login(LoginDto model) {
        try {
            request.getSession().invalidate();
            if(request.getCookies() != null){
                request.getCookies()[0].setMaxAge(0);
            }
            List<TybUser> list = new ArrayList<TybUser>();
            list = tybUserService.findAllByKey(Tools.getMap("userName", model.getUserName()), ".findNameByUser");
            if (list.size() > 0) {
                TybUser user = list.get(0);
                if (!Tools.isEmpty(model.getPassword())
                        //&& Md5Util.getEncryptedPwd(model.getPassword()).equals(user.getPassword())
                        && model.getPassword().trim().equals(user.getPassword()) && model.getUserName().equals(user.getUserName())) {
                    request.getSession().setAttribute("loginUser",user);
                    //tybUserService.login(model, user, request, response);
                    return AjaxDone.successCloseRel("jbsxBox");
                }
                //model.setTipMessage("用户密码有误");
                return AjaxDone.error("用户密码有误");
                //return new JsonResult(model);
            } else {
                //model.setTipMessage("用户名不存在");
                //return new JsonResult(model);
                return AjaxDone.error("用户名不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //model.setTipMessage("未知异常，请看日志：" + e.getMessage());
            //return new JsonResult(model);
            return AjaxDone.error("未知异常，请看日志");
        }
    }

    /**
     * @Author: TYB
     * @Date: 2017-04-09 下午 4:53
     * @Des: 跳转主页面
     */

    //@AuthPassport
    @RequestMapping(value = "admin")
    public String admin() {
        return "web/index";
    }

    @RequestMapping(value = "/loginOut")
    public String loginOut() {
        request.getSession().invalidate();
        if(request.getCookies() != null){
            request.getCookies()[0].setMaxAge(0);
        }
        return "web/login";
    }
    @RequestMapping(value = "/reLogin",method = RequestMethod.GET)
    public String reLogin() {
        request.getSession().invalidate();
        if(request.getCookies() != null){
            request.getCookies()[0].setMaxAge(0);
        }
        return "web/reLogin";
    }
}
