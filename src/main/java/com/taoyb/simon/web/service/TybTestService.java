package com.taoyb.simon.web.service;


import com.taoyb.simon.common.base.BaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybTest;
import com.taoyb.simon.web.utils.AjaxDone;

/**
 * Created by taoyb on 2016-12-05.
 */
public interface TybTestService extends BaseDao<TybTest,Long> {
    Pager<TybTest> findAllTests(TybTest test, Integer pageNum, Integer pageSize);
    AjaxDone saveTest(TybTest test, String nav);
    AjaxDone delTest(Long tid);
}
