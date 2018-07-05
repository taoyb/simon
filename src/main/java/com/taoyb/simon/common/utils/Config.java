package com.taoyb.simon.common.utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by taoyb on 2017-04-09.
 * 获取jdbc中数据（根据键名 获取键值）
 *  使用方式必须在当前类使用@Component，xml文件内配置的是通过pakage扫描方式,
 *  例如：<context:property-placeholder location="classpath:jdbc.properties,classpath:mail.properties"/>
 *  spring只允许有一个property-placeholder
 */
@Component
public class Config {
    @Value("${web.redisIp}")
    private String redisIp;
    public String getRedisIp() {
        return redisIp;
    }
    @Value("${web.loginInforTime}")
    private Integer loginInforTime;
    public Integer getLoginInforTime() {
        return loginInforTime;
    }
    @Value("${pro.belong}")
    private String proBelong;
    public String getProBelong() {
        return proBelong;
    }
    @Value("${wechat.token}")
    private String wechatToken;
    public String getWechatToken() {
        return wechatToken;
    }
}
