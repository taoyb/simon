package com.taoyb.simon.web.service;



import com.taoyb.simon.common.base.BaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybMenus;
import com.taoyb.simon.web.utils.AjaxDone;
import com.taoyb.simon.web.utils.Tree;

import java.util.List;

/**
 * Created by taoyb on 2016-12-05.
 */
public interface TybMenusService extends BaseDao<TybMenus,Long> {

    public List<Tree> findMenuByTree(String pid, String menuBelong);

    public List<TybMenus> findMenuByType(String menuType);

    public List<TybMenus> findAllByParentId(Long parentid, String menuBelong);

    public Pager<TybMenus> findMenuByParentIdPager(Long parentid, Integer pagerNum, Integer pagerSize);

    public AjaxDone saveOrUpdateMenu(TybMenus menus);

    public AjaxDone delMenu(Long mid);
    /**
     * @Author TYB
     * @Date 2018-01-23 下午 3:21
     * @Desc 菜单排序
     */
    public AjaxDone sortMenu(String[] ids);

}
