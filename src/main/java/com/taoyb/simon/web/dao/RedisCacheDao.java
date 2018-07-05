package com.taoyb.simon.web.dao;
/**
 * Created by taoyb on 2017-04-09.
 * 以reids缓存形式
 */
public class RedisCacheDao implements ICacheDao {
    @Override
    public Object getObj(String key) {
        return null;
    }
    @Override
    public boolean setStr(String key, String value, int expireTime) {
        return false;
    }
    @Override
    public String getStr(String key) {
        return null;
    }
    @Override
    public boolean setObj(String key, Object value, int expireTime) {
        return false;
    }
    @Override
    public boolean delStr(String key) {
        return false;
    }
    @Override
    public boolean delObj(String key) {
        return false;
    }
    @Override
    public boolean delObj(String key, String field) {
        return false;
    }
    @Override
    public boolean setObj(String key, String field, Object value, int expireTime) {
        return false;
    }
    @Override
    public Object getObj(String key, String field) {
        return null;
    }
    @Override
    public boolean flushDB() {
        return false;
    }
}
