package com.taoyb.simon.web.utils;

import com.taoyb.simon.common.SpringContextHolder;
import com.taoyb.simon.web.model.TybUser;
import com.taoyb.simon.web.service.ICacheService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyb on 2017-02-27.
 * 工具类
 */
public class Tools {
    /**
      * @Author: TYB
      * @Date: 2017-02-27 下午 5:20
      * @Des: 判断空
      */
    public static boolean isEmpty(Object object){
        if(object instanceof String){
            if(object == null||object.toString().trim().equals("")||object.toString().trim().equalsIgnoreCase("null")||object.toString().equals("undefined"))
                return true;
        }else if(object instanceof List<?>){
            if(object == null ||((List<?>)object).size()==0)
                return true;
        }else if(object == null){
            return true;
        }
        return false;
    }

    /**
      * @Author: TYB
      * @Date: 2017-02-27 下午 5:00
      * @Des: 构造查询Map集合 @param 不定数量参数 格式(key1,value1,key2,value2....)
      */
    public static Map<String, Object> getMap(Object... params) {
        if (params.length == 0 || params.length % 2 != 0) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < params.length; i = i + 2) {
            if (!Tools.isEmpty(params[i + 1]))
                map.put(params[i].toString(), params[i + 1]);
        }
        return map;
    }

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取用户登录信息
     * @return
     */
    public static TybUser getUser(){
        ICacheService cacheService = SpringContextHolder.getBean("cacheService");
        String uId = MyCookie.getCookie(Simon.COOKIE_USERID, false, Tools.getRequest());
        return (TybUser)cacheService.getObj(Simon.CACHE_USER + uId);
    }
}
