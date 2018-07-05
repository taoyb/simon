package com.taoyb.simon.web.model;

import java.io.Serializable;

/**
 * Created by taoyb on 2016-12-11.
 * 菜单
 */
public class TybMenus implements Serializable {
    public final static Integer STAT_JY = 0;//禁用
    public final static Integer STAT_QY = 1;//启用
    public final static Integer STAT_FJ = 2;//有子级
    private Long menuId;//菜单id
    private String menuName;//菜单名称
    private Long menuParentid;//父级id
    private String menuUrl;//菜单路径
    private String imgUrl;//图片路径
    private int imgWidth;//图片宽度
    private int imgHeight;//图片高度

    public String getMenuBelong() {
        return menuBelong;
    }

    public void setMenuBelong(String menuBelong) {
        this.menuBelong = menuBelong;
    }

    private int menuStat;//菜单状态
    private String menuBelong;//菜单属于...
    private Integer orderId;
    public Integer getOrderId() {
        return orderId;
    }
    public TybMenus setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public TybMenus setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
    public Long getMenuId() {
        return menuId;
    }
    public TybMenus setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }
    public String getMenuName() {
        return menuName;
    }
    public TybMenus setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }
    public Long getMenuParentid() {
        return menuParentid;
    }
    public TybMenus setMenuParentid(Long menuParentid) {
        this.menuParentid = menuParentid;
        return this;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public TybMenus setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
        return this;
    }
    public int getImgWidth() {
        return imgWidth;
    }
    public TybMenus setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
        return this;
    }
    public int getImgHeight() {
        return imgHeight;
    }
    public TybMenus setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
        return this;
    }
    public int getMenuStat() {
        return menuStat;
    }
    public TybMenus setMenuStat(int menuStat) {
        this.menuStat = menuStat;
        return this;
    }
}
