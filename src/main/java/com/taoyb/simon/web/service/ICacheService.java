package com.taoyb.simon.web.service;
/**
 * Created by taoyb on 2017-04-09.
 * 缓存service
 */
public interface ICacheService {
    Object setObj(String key, Object value, int expireTime);
    Object getObj(String key);
    boolean delObj(String key);
}
