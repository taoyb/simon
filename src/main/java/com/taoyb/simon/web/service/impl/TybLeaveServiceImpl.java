package com.taoyb.simon.web.service.impl;

import com.taoyb.simon.common.base.AbstractBaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybLeave;
import com.taoyb.simon.web.service.TybLeaveService;
import com.taoyb.simon.web.utils.AjaxDone;
import com.taoyb.simon.web.utils.EnumUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
@Service
public class TybLeaveServiceImpl extends AbstractBaseDao<TybLeave,Long> implements TybLeaveService {
   /* @Autowired
    private Config config;*/
    @Override
    public Pager<TybLeave> findAllLeaves(TybLeave leave, Integer pageNum, Integer pageSize) {
        try {
            Map<String,Object> maps = new HashMap<String,Object>();
            maps.put("pageNo",(pageNum-1)*pageSize);
            maps.put("pageSize",pageSize);
            return findByKey(maps,".findAllLeaves");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pager<TybLeave>();
    }

    @Override
    public AjaxDone saveOrUpdateLeave(TybLeave leave) {
        try {
            if (leave.getLeaveId()!=null){
                this.updateEntity(leave);
            }else {
                leave.setLeaveDate(new Date());
                leave.setLeaveState(EnumUtils.LCWTJ.getValue());
                this.addEntity(leave);
            }
            return AjaxDone.successClose();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }

    @Override
    public AjaxDone deleteLeave(Long leaveId) {
        try {
            this.delEntity(leaveId);
            return AjaxDone.success("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error("删除失败");
        }
    }
}
