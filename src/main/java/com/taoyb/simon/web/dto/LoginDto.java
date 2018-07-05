package com.taoyb.simon.web.dto;
import java.io.Serializable;

/**
 * Created by taoyb on 2017-02-20.
 * 登陆
 */
public class LoginDto implements Serializable {
    private String id;
    private String userName; //用户名
    private String password;//用户密码
    private String rpassword;//确认密码
    private String sessionUserName;//如何等于null标识未登录
    private String tipMessage;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRpassword() {
        return rpassword;
    }
    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }
    public String getSessionUserName() {
        return sessionUserName;
    }
    public void setSessionUserName(String sessionUserName) {
        this.sessionUserName = sessionUserName;
    }
    public String getTipMessage() {
        return tipMessage;
    }
    public void setTipMessage(String tipMessage) {
        this.tipMessage = tipMessage;
    }
}
