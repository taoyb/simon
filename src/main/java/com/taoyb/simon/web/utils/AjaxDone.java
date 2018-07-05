package com.taoyb.simon.web.utils;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
public class AjaxDone {
    private Integer statusCode;
    private String message;
    private String navTabId;
    private String rel;
    private String callbackType;//callbackType如果是closeCurrent就会关闭当前tab
    private String forwardUrl;// 只有callbackType="forward"时需要forwardUrl值
    public static AjaxDone success(){
        AjaxDone ad = new AjaxDone();
        ad.setStatusCode(200);
        ad.setMessage("\u64cd\u4f5c\u6210\u529f");//将文字进行ASCLL编码转换
        return ad;
    }
    public static AjaxDone success(String message){
        AjaxDone ad = new AjaxDone();
        ad.setStatusCode(200);
        ad.setMessage(message);//将文字进行ASCLL编码转换
        return ad;
    }
    public static AjaxDone successClose(){
        AjaxDone ad = success();
        ad.setCallbackType("closeCurrent");
        return ad;
    }
    public static AjaxDone successRel(String rel){
        AjaxDone ad = success();
        ad.setRel(rel);
        return ad;
    }
    public static AjaxDone successCloseRel(String rel){
        AjaxDone ad = successRel(rel);
        ad.setCallbackType("closeCurrent"); //callbackType如果是closeCurrent就会关闭当前tab
        return ad;
    }
    public static AjaxDone successCloseNav(String nav){
        AjaxDone ad = success();
        ad.setNavTabId(nav);
        ad.setCallbackType("closeCurrent");
        return ad;
    }
    public static AjaxDone error(){
        AjaxDone ad = new AjaxDone();
        ad.setStatusCode(300);
        ad.setMessage("\u64cd\u4f5c\u5931\u8d25");
        return ad;
    }
    public static AjaxDone error(String message){
        AjaxDone ad = new AjaxDone();
        ad.setStatusCode(300);
        ad.setMessage(message);
        return ad;
    }
    public static AjaxDone errorOvertime(){
        AjaxDone ad = new AjaxDone();
        ad.setStatusCode(300);
        ad.setMessage("登录已超时，请重新登录！");
        return ad;
    }
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

}
