package com.taoyb.simon.web.utils;

/**
 * Created by taoyb on 2018-01-09.
 */
public enum EnumUtils {
    LCWTJ("未提交",1),LCSHZ("审核中",2),LCSHTG("审核通过",3),LCSHBH("审核驳回",4);

    //部署成功，部署ID=
    public final static String BSLCCG = "部署成功，部署ID=";
    //根据模型部署流程失败：modelId=
    public final static String BSLCSB = "根据模型部署流程失败：modelId=";


    // 成员变量
    private String value;
    private int index;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    // 构造方法
    private EnumUtils(String value, int index) {
        this.value = value;
        this.index = index;
    }
}