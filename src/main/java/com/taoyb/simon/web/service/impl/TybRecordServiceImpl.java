package com.taoyb.simon.web.service.impl;


import com.taoyb.simon.common.base.AbstractBaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybRecord;
import com.taoyb.simon.web.service.TybRecordService;
import com.taoyb.simon.web.utils.AjaxDone;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
@Service
public class TybRecordServiceImpl extends AbstractBaseDao<TybRecord,Long> implements TybRecordService {


    @Override
    public Pager<TybRecord> findAllRecords(TybRecord record, Integer pageNum, Integer pageSize) {
        try {
            Map<String,Object> maps = new HashMap<String,Object>();
            if (record != null && StringUtils.isNotEmpty(record.getRecordName())){
                maps.put("recordName",record.getRecordName());
            }
            maps.put("pageNo",(pageNum-1)*pageSize);
            maps.put("pageSize",pageSize);
            return findByKey(maps,".findAllRecords");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pager<TybRecord>();
    }

    @Override
    public AjaxDone saveRecord(TybRecord record, String nav) {
        try {
            if (record.getRecordId()!=null){
                this.updateEntity(record);
            }else {
                this.addEntity(record);
            }
            return AjaxDone.successCloseNav(nav);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }

    @Override
    public AjaxDone delRecord(Long rid) {
        try {
            this.delEntity(rid);
            return AjaxDone.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }
}
