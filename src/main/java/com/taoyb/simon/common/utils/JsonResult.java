package com.taoyb.simon.common.utils;
import com.taoyb.simon.web.dto.LoginDto;

import java.io.Serializable;

/**
 * Created by taoyb on 2017-01-05.
 * json处理类
 */
public class JsonResult implements Serializable {
    private Pager pager;//分页列表集合
    private Object data;//数据存放
    private Integer code;
    private Object others;//传递至前端的其他参数
    private  ErrorMessage error;


    public JsonResult(String errorCode){
        this.data = null;
        this.code = 0;
        String errorMsg =  ErrorInfos.getMessage(errorCode);
        this.setError( new ErrorMessage(errorCode,errorMsg) );
    }
    public JsonResult(MyException exception){
        this.data = null;
        this.code = 0;
        String errorCode = exception.getMessage();
        String errorMsg =  ErrorInfos.getMessage(errorCode);
        this.setError( new ErrorMessage(errorCode,errorMsg+(exception.getMsgExtention()==null?"":exception.getMsgExtention())));
    }
    public JsonResult(Object data){
        this.code = 1;
        this.data = data;
    }
    public JsonResult(Object data,Pager pager){
        this.data = data;
        this.code = 1;
        this.pager = pager;
    }
    public JsonResult(Object data,Pager pager,Object others){
        this(data, pager);
        this.others = others;
    }
    //json返回是否成功
    public JsonResult(Integer code,Object data,String errorCode,String errorMessage) {
        this.data = data;
        this.code = code;
        if(code == 0){
            this.error = new ErrorMessage(errorCode,errorMessage);
        }
    }
    public Pager getPager() {
        return pager;
    }
    public JsonResult setPager(Pager pager) {
        this.pager = pager;
        return this;
    }
    public Object getData() {
        return data;
    }
    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }
    public Integer getCode() {
        return code;
    }
    public JsonResult setCode(Integer code) {
        this.code = code;
        return this;
    }
    public Object getOthers() {
        return others;
    }
    public JsonResult setOthers(Object others) {
        this.others = others;
        return this;
    }
    public ErrorMessage getError() {
        return error;
    }
    public JsonResult setError(ErrorMessage error) {
        this.error = error;
        return this;
    }
}
class ErrorMessage{
    private String code;
    private String message;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(String code,String message){
        this.setCode(code);
        this.setMessage(message);
    }
}
