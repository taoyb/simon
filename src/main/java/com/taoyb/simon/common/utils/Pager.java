package com.taoyb.simon.common.utils;
import java.io.Serializable;
import java.util.List;

/**
 * Created by taoyb on 2016-12-07.
 * 分页查询
 */
public class Pager<T> implements Serializable {
    private Integer totalNum;//总数
    private Integer pageSize = 15;//每页显示条数
    private Integer pageNum; //当前页
    private List<T> pageList;

    public Integer getPageNum() {
        return pageNum;
    }
    public Pager setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }
    public Integer getTotalNum() {
        return totalNum;
    }
    public Pager setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public Pager setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    public List<T> getPageList() {
        return pageList;
    }
    public Pager setPageList(List<T> pageList) {
        this.pageList = pageList;
        return this;
    }
}
