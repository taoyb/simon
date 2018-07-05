package com.taoyb.simon.common.utils;
/**
 * Created by taoyb on 2016-12-07.
 * 判断
 */
public class JudgeUtils {
    /**
     * 判断文件后缀名的值
     * @param strUrl
     * @return
     */
    public static String getStrUrl(String strUrl){
        String url="";
        if(strUrl.endsWith(".png")){
            url=".png";
        }
        if(strUrl.endsWith(".ico")){
            url=".ico";
        }
        if(strUrl.endsWith(".gif")){
            url=".gif";
        }
        if(strUrl.endsWith(".js")){
            url=".js";
        }
        if(strUrl.endsWith(".css")){
            url=".css";
        }
        return url;
    }
}
