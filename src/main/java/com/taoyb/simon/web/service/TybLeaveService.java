package com.taoyb.simon.web.service;


import com.taoyb.simon.common.base.BaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybLeave;
import com.taoyb.simon.web.utils.AjaxDone;

/**
 * Created by taoyb on 2016-12-05.
 */
public interface TybLeaveService extends BaseDao<TybLeave,Long> {
    Pager<TybLeave> findAllLeaves(TybLeave leave, Integer pageNum, Integer pageSize);
    public AjaxDone saveOrUpdateLeave(TybLeave leave);
    public AjaxDone deleteLeave(Long leaveId);
}
