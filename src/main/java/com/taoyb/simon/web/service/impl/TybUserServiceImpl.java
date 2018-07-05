package com.taoyb.simon.web.service.impl;

import com.taoyb.simon.common.base.AbstractBaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybUser;
import com.taoyb.simon.web.service.TybUserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
@Service
public class TybUserServiceImpl extends AbstractBaseDao<TybUser,Long> implements TybUserService {
    @Override
    public Pager<TybUser> findAllUsers(Integer currentPage) {
        try {
            Map<String,Object> maps = new HashMap<String,Object>();
            maps.put("pageNo",currentPage==null?0:currentPage);
            maps.put("pageSize",15);
            return findByKey(maps,".findAllUsers");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pager<TybUser>();
    }
}
