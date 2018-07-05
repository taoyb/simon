package com.taoyb.simon.web.service;


import com.taoyb.simon.common.base.BaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by taoyb on 2016-12-05.
 */
public interface TybUserService extends BaseDao<TybUser,Long> {
   /* TybUser login(String username, String password);
    void login(LoginDto model, TybUser user, HttpServletRequest request, HttpServletResponse response);*/
    Pager<TybUser> findAllUsers(Integer currentPage);
}

