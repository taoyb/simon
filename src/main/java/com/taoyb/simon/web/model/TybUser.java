package com.taoyb.simon.web.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by taoyb on 2016-12-02.
 */
public class TybUser implements Serializable{
    private Long userId;//用户id
    private String userName;//用户名
    private Long userPhone;//用户手机
    private String password;//用户密码
    private Long orgId;//所属岗位
    private Integer userAge;//用户年龄
    private String email;//邮箱
    private String realName;//真实姓名
    @DateTimeFormat(pattern ="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;//创建时间
    @DateTimeFormat(pattern ="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;//操作时间
    private Integer orderId;//排序字段
    public Long getUserId() {
        return userId;
    }
    public TybUser setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public String getUserName() {
        return userName;
    }
    public TybUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public Long getUserPhone() {
        return userPhone;
    }
    public TybUser setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public TybUser setPassword(String password) {
        this.password = password;
        return this;
    }
    public Long getOrgId() {
        return orgId;
    }
    public TybUser setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }
    public Integer getUserAge() {
        return userAge;
    }
    public TybUser setUserAge(Integer userAge) {
        this.userAge = userAge;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public TybUser setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getRealName() {
        return realName;
    }
    public TybUser setRealName(String realName) {
        this.realName = realName;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public TybUser setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public TybUser setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
    @Override
    public String toString() {
        return "TybUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone=" + userPhone +
                ", password='" + password + '\'' +
                ", orgId=" + orgId +
                ", userAge=" + userAge +
                ", email='" + email + '\'' +
                ", realName='" + realName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
