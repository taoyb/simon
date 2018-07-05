package com.taoyb.simon.web.service.impl;


import com.taoyb.simon.common.base.AbstractBaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybTest;
import com.taoyb.simon.web.service.TybTestService;
import com.taoyb.simon.web.utils.AjaxDone;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
@Service
public class TybTestServiceImpl extends AbstractBaseDao<TybTest,Long> implements TybTestService {


    @Override
    public Pager<TybTest> findAllTests(TybTest test, Integer pageNum, Integer pageSize) {
        try {
            Map<String,Object> maps = new HashMap<String,Object>();
            if (test != null && StringUtils.isNotEmpty(test.getTestName())){
                maps.put("testName",test.getTestName());
            }
            maps.put("pageNo",(pageNum-1)*pageSize);
            maps.put("pageSize",pageSize);
            return findByKey(maps,".findAllTests");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pager<TybTest>();
    }

    @Override
    public AjaxDone saveTest(TybTest test, String nav) {
        try {
            this.addEntity(test);
            return AjaxDone.successCloseNav(nav);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }

    @Override
    public AjaxDone delTest(Long tid) {
        try {
            this.delEntity(tid);
            return AjaxDone.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }
}
