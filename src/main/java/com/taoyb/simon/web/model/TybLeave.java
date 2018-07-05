package com.taoyb.simon.web.model;

import java.util.Date;

/**
 * Created by taoyb on 2018-01-09.
 * 请假
 */
public class TybLeave {

    private Long leaveId; // 编号
    private TybUser user; // 请假人
    private Date leaveDate;// 请假日期
    private Integer leaveDay; // 请假天数
    private String leaveReason; // 请假原因
    private String leaveState; // 审核状态  未提交  审核中 审核通过 审核未通过
    private String processInstanceId; // 流程实例Id

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public TybUser getUser() {
        return user;
    }

    public void setUser(TybUser user) {
        this.user = user;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Integer getLeaveDays() {
        return leaveDay;
    }

    public void setLeaveDays(Integer leaveDay) {
        this.leaveDay = leaveDay;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveState() {
        return leaveState;
    }

    public void setLeaveState(String leaveState) {
        this.leaveState = leaveState;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
