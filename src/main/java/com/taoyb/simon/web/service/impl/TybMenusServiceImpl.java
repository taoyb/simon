package com.taoyb.simon.web.service.impl;

import com.taoyb.simon.common.base.AbstractBaseDao;
import com.taoyb.simon.common.utils.Config;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybMenus;
import com.taoyb.simon.web.service.TybMenusService;
import com.taoyb.simon.web.utils.AjaxDone;
import com.taoyb.simon.web.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
@Service
public class TybMenusServiceImpl extends AbstractBaseDao<TybMenus,Long> implements TybMenusService {
    @Autowired
    private Config config;
    @Override
    public List<TybMenus> findAllByParentId(Long parentid, String menuBelong) {
        Map map = new HashMap<>();
        map.put("parentid",parentid);
        map.put("menuBelong",menuBelong);
        return this.findAllByKey(map,".findMenuByParentId");
    }

    @Override
    public Pager<TybMenus> findMenuByParentIdPager(Long parentid, Integer pageNo, Integer pageSize) {
        Map map = new HashMap<>();
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("parentid",parentid);
        map.put("menuBelong",config.getProBelong());
        return this.findByKey(map,".findMenuByParentIdPager");
    }

    @Override
    public AjaxDone saveOrUpdateMenu(TybMenus menus) {
        try {
            if (menus.getMenuId()!=null){
                this.updateEntity(menus);
            }else {
                menus.setMenuBelong(config.getProBelong());
                this.addEntity(menus);
            }
            return AjaxDone.successCloseRel("jbsxBox");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }

    @Override
    public AjaxDone delMenu(Long mid) {
        try {
            this.delEntity(mid);
            return AjaxDone.successRel("jbsxBox");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }

    @Override
    public AjaxDone sortMenu(String[] ids) {
        try {
            TybMenus menusSour = findById(Long.parseLong(ids[2]));
            TybMenus menus = findById(Long.parseLong(ids[0]));
            if (ids[1].equals("down")){
                menus.setOrderId(menusSour.getOrderId()-1);
            }else {
                menus.setOrderId(menusSour.getOrderId()+1);
            }
            this.updateEntity(menus);
            return AjaxDone.success("菜单顺序调整成功");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return AjaxDone.error("菜单顺序调整失败");
        }
    }

    @Override
    public List<Tree> findMenuByTree(String pid, String menuBelong) {
        List<TybMenus> list =  this.findAllByParentId(pid==null?0l:Long.parseLong(pid),menuBelong);
        List<Tree> treeList = new ArrayList<Tree>();
        for (TybMenus menus : list) {
            Tree tree = new Tree();
            tree.setTarget("ajax");
            tree.setRel("jbsxBox");
            tree.setId(menus.getMenuId());
            tree.setName(menus.getMenuName());
            tree.setType("0");
            int count = this.findAllByParentId(menus.getMenuId(),menuBelong).size();
            if(count>0){
                tree.setIsParent(true);
                tree.setOpen(true);
            }else{
                tree.setIsParent(false);
            }
            tree.setpId(menus.getMenuParentid());
            tree.setUrl("/menus/menuList?parentId="+menus.getMenuId());
            treeList.add(tree);
        }
        return treeList;
    }

    @Override
    public List<TybMenus> findMenuByType(String menuType) {
        Map map = new HashMap<>();
        map.put("menuType",menuType);
        return this.findAllByKey(map,".findMenuByType");
    }
}
