package com.taoyb.simon.web.service.impl;


import com.taoyb.simon.common.base.AbstractBaseDao;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybRecord;
import com.taoyb.simon.web.service.TybRecordService;
import com.taoyb.simon.web.utils.AjaxDone;
import com.taoyb.simon.web.utils.Tree;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
@Service
public class TybRecordServiceImpl extends AbstractBaseDao<TybRecord, Long> implements TybRecordService {


    @Override
    public Pager<TybRecord> findAllRecords(TybRecord record, Integer pageNum, Integer pageSize) {
        try {
            Map<String, Object> maps = new HashMap<String, Object>();
            if (record != null && StringUtils.isNotEmpty(record.getRecordName())) {
                maps.put("recordName", record.getRecordName());
            }
            maps.put("pageNo", (pageNum - 1) * pageSize);
            maps.put("pageSize", pageSize);
            return findByKey(maps, ".findAllRecords");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pager<TybRecord>();
    }



    @Override
    public AjaxDone saveRecord(TybRecord record, String nav) {
        try {
            if (record.getRecordId() != null) {
                this.updateEntity(record);
            } else {
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

    @Override
    public List<TybRecord> findByParentId(Long pid) {
        Map map = new HashMap<String, String>();
        map.put("parentId", pid);
        return this.findAllByKey(map, ".findByParentId");
    }

    @Override
    public List<Tree> findTreeRecordAll(String pid,Boolean isLoadAll) {
        List<TybRecord> list = this.findByParentId(pid == null ? 0l : Long.parseLong(pid));
        List<Tree> treeList = new ArrayList<Tree>();
        /*if (list.size() == 0) {
            Tree tree = new Tree();
            tree.setTarget("ajax");
            tree.setRel("jbsxBox");
            tree.setId(0l);
            tree.setName("临时分类");
            tree.setType("0");
            tree.setIsParent(false);
            tree.setpId(-1l);
            treeList.add(tree);
            return treeList;
        }*/
        for (TybRecord record : list) {
            Tree tree = new Tree();
            tree.setTarget("ajax");
            tree.setRel("jbsxBox1");
            tree.setId(record.getRecordId());
            tree.setName(record.getRecordName().trim());
            tree.setType("0");
            List<Tree> children = findTreeRecordAll(record.getRecordId().toString(),isLoadAll);
            if (children.size()>0) {
                tree.setIsParent(true);
                if(isLoadAll) tree.setChildren(children);
            } else {
                tree.setIsParent(false);
            }
            tree.setpId(record.getRecordParentId());
            tree.setUrl("/record/addContent?recordId=" + record.getRecordId());
            treeList.add(tree);
        }
        return treeList;
    }

    @Override
    public Tree toTransforTreeByRecord(TybRecord record) {
        Tree tree = new Tree();
        tree.setTarget("ajax");
        tree.setRel("jbsxBox1");
        tree.setId(record.getRecordId());
        tree.setName(record.getRecordName().trim());
        tree.setType("0");
        int count = this.findByParentId(record.getRecordId()).size();
        if (count > 0) {
            tree.setIsParent(true);
        } else {
            tree.setIsParent(false);
        }
        tree.setpId(record.getRecordParentId());
        tree.setUrl("/record/addContent?recordId=" + record.getRecordId());
        return tree;
    }
}
