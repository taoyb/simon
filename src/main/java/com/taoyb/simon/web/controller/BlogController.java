package com.taoyb.simon.web.controller;

import com.taoyb.simon.web.model.TybMenus;
import com.taoyb.simon.web.utils.BaseController;
import com.taoyb.simon.web.utils.Tree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: tyb
 * @Date: 18-7-9 下午2:26
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {

    @RequestMapping(value = "/findHeadMenu")
    public ModelAndView formAll() {
        List<TybMenus> list = tybMenusService.findMenuByType(TybMenus.TYPE_BLOG);
        ModelAndView model = new ModelAndView("blog/index");
        model.addObject("list", list);
        return model;
    }

    @RequestMapping(value = "/record_main", method = RequestMethod.GET)
    public String recordMain() {
        return "blog/record";
    }

    @RequestMapping(value = "/record_left",method = RequestMethod.POST)
    public @ResponseBody
    List<Tree> recordLeft(HttpServletRequest request) {
        String pid = request.getParameter("id");
        List<Tree> list= tybMenusService.findMenuByTree(pid,"web");
        return list;
    }

}
