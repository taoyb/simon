package com.taoyb.simon.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by taoyb on 2017-08-29.
 * 记录
 */
public class TybRecord implements Serializable{
    private Long recordId;  //id
    private Date ctime;
    private String recordName;//名称
    private String recordDesc;//详情
    private Long recordParentId;

    public Long getRecordId() {
        return recordId;
    }

    public TybRecord setRecordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getRecordName() {
        return recordName;
    }

    public TybRecord setRecordName(String recordName) {
        this.recordName = recordName;
        return this;
    }

    public String getRecordDesc() {
        return recordDesc;
    }

    public TybRecord setRecordDesc(String recordDesc) {
        this.recordDesc = recordDesc;
        return this;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Long getRecordParentId() {
        return recordParentId;
    }

    public void setRecordParentId(Long recordParentId) {
        this.recordParentId = recordParentId;
    }
}
