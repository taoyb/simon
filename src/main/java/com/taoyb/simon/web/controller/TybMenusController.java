package com.taoyb.simon.web.controller;


import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybMenus;
import com.taoyb.simon.web.utils.AjaxDone;
import com.taoyb.simon.web.utils.BaseController;
import com.taoyb.simon.web.utils.Tree;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by taoyb on 2016-12-05.
 */
@Controller
@RequestMapping("/menus")
public class TybMenusController extends BaseController {
    /**
     * @Author: TYB
     * @Date: 2017-01-05 下午 2:57
     * @Des: 初始化一级菜单
     *//*
    @RequestMapping(value = "/initFirstLevelMenu", method = RequestMethod.POST)
    public @ResponseBody JsonResult initFirstLevelMenu(HttpServletRequest request) {
        List<TybMenus> list = tybMenusService.findAllByParentId(0l);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("firstLevelMenu", list);
        String leftMenuId = request.getParameter("levelMenuId") == null ? list.size() > 0 ? list.get(0).getMenuId().toString() : "" : request.getParameter("levelMenuId");
        String leftMenu =   tybMenusService.loadSubMenu(leftMenuId);
        maps.put("leftMenu", leftMenu);
        return new JsonResult( maps);
    }
    *//**
     * @Author: TYB
     * @Date: 2017-01-12 上午 11:47
     * @Des: 用户管理
     *//*
    @RequestMapping(value = "/userManagement", method = RequestMethod.GET)
    public String userManagement() {
        return "web/system/users";
    }*/

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "menu/menu_all";
    }

    @RequestMapping(value = "/treeLeft",method = RequestMethod.POST)
    public @ResponseBody
    List<Tree> treeList(HttpServletRequest request) {
        String pid = request.getParameter("id");
        List<Tree> list= tybMenusService.findMenuByTree(pid,"web");
        return list;
    }
    @RequestMapping(value = "/menuList")
    public String menuList(Model model, Long parentId) {
        String pageNo = request.getParameter("pageNum");
        String pageSize = request.getParameter("numPerPage");
        Pager<TybMenus> pager = tybMenusService.findMenuByParentIdPager(parentId,StringUtils.isEmpty(pageNo)?1:(Integer.parseInt(pageNo)),StringUtils.isEmpty(pageSize)?20:Integer.parseInt(pageSize));
        model.addAttribute("pager",pager);
        model.addAttribute("parentId",parentId);
        return "menu/menuList";
    }
    @RequestMapping(value = "/menuToAdd", method = RequestMethod.GET)
    public String menuToAdd(Model model) {
        String pid = request.getParameter("pid");
        String mid = request.getParameter("mid");
        if (StringUtils.isNotEmpty(mid)){
            TybMenus menu = tybMenusService.findById(Long.parseLong(mid));
            model.addAttribute("menu",menu);
        }
        model.addAttribute("pid",pid);
        return "menu/menu_add";
    }
    @RequestMapping(value = "/menuAdd",method = RequestMethod.POST)
    public @ResponseBody AjaxDone menuAdd(TybMenus menus, Model model) {
        return tybMenusService.saveOrUpdateMenu(menus);
    }
    @RequestMapping(value = "/menuDel",method = RequestMethod.POST)
    public @ResponseBody AjaxDone menuDel(Long mid) {
        return tybMenusService.delMenu(mid);
    }
    /**
     * @Author TYB
     * @Date 2018-01-23 下午 3:17
     * @Desc 菜单排序
     */
    @RequestMapping(value = "/menuSort",method = RequestMethod.POST)
    public @ResponseBody AjaxDone menuSort(String[] ids) {
        return tybMenusService.sortMenu(ids);
    }
}
