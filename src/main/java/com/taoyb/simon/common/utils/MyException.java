package com.taoyb.simon.common.utils;

/**
 * Created by taoyb on 2017-04-16.
 */
public class MyException extends Exception {
    private String msgExtention;
    public MyException(String errorCode) {
        super(errorCode);
    }

    public MyException(String errorCode,String msgExtention) {
        super(errorCode);
        this.setMsgExtention(msgExtention);
    }
    public String getMsgExtention() {
        return msgExtention;
    }

    public void setMsgExtention(String msgExtention) {
        this.msgExtention = msgExtention;
    }
}
