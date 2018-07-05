package com.taoyb.simon.web.service.impl;
import com.taoyb.simon.common.utils.Config;
import com.taoyb.simon.web.dao.ICacheDao;
import com.taoyb.simon.web.dao.MemoryCacheDao;
import com.taoyb.simon.web.dao.RedisCacheDao;
import com.taoyb.simon.web.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by taoyb on 2017-04-09.
 * 缓存实现service
 */
@Service
public class CacheService implements ICacheService{
    @Autowired
    private Config config;
    @Resource
    private ICacheDao memoryCacheDao;
    @Resource
    private ICacheDao redisCacheDao;
    private ICacheDao getDao(){
        if( config.getRedisIp().trim().equals("") ){
            return memoryCacheDao;
        }else{
            return redisCacheDao;
        }
    }
    @Override
    public Object setObj(String key, Object value, int expireTime) {
        return getDao().setObj(key,value,expireTime);
    }

    @Override
    public Object getObj(String key) {
        return getDao().getObj(key);
    }

    @Override
    public boolean delObj(String key) {
        return getDao().delObj(key);
    }
}
