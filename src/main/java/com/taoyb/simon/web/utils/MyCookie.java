package com.taoyb.simon.web.utils;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyCookie {
	/**
	  * @Author: TYB
	  * @Date: 2017-02-20 下午 4:44
	  * @Des: 清空cookie
	  */
	public static void deleteCookie(String key, HttpServletRequest request, HttpServletResponse response){
		Cookie[] allCookie= request.getCookies();
		if(allCookie!=null&&allCookie.length!=0)
		 {
		     for(int i=0;i<allCookie.length;i++)
		     {
		          String keyname= allCookie[i].getName();
		          if(key.equals(keyname))
		          {
		        	  allCookie[i].setValue(null);
		        	  allCookie[i].setMaxAge(0);
		        	  response.addCookie(allCookie[i]);
		          }
		      }
		 }
	}
	public static void addCookie(String key, String value, HttpServletResponse response, int time){
		addCookie(key,value,false, response, time);
	}
	public static void addCookie(String key,String value, HttpServletResponse response){
		addCookie(key,value,false, response,60*60*24*7);
	}
	public static void addCookie(String key,String value,boolean isBoo, HttpServletResponse response){
		addCookie(key,value,isBoo, response,60*60*24*7);
	}
	public static void addCookie(String key, String value, boolean isBoo, HttpServletResponse response, int time){
		if(isBoo){
			value = Aes.encrypt(value);
		}else{
			value =new String(new Base64().encode(value.getBytes()));
		}
		Cookie myCookie=new Cookie(key,value);
		myCookie.setMaxAge(time);
		// 多个路径下可以共享cookie
		myCookie.setPath("/");
		response.addCookie(myCookie);
	}
	public static String getCookie(String key,HttpServletRequest request){
		return getCookie(key,false,request);
	}
	public static String getCookie(String key,boolean isBoo, HttpServletRequest request){
		Cookie allCookie[]= request.getCookies();
		if(allCookie!=null&&allCookie.length!=0)
		 {
		     for(int i=0;i<allCookie.length;i++)
		     {
		          String keyname= allCookie[i].getName();
		          if((key).equals(keyname))
		          {
					  if(allCookie[i].getValue()==null)
						  return "";
					  else if(isBoo)
						return Aes.desEncrypt(allCookie[i].getValue());
					  else
						  return new String(new Base64().decode(allCookie[i].getValue()));
		          }
		      }
		 }
		return "";
	}
}
