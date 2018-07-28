package com.taoyb.simon.common.base;
import com.taoyb.simon.common.utils.Pager;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
public class TDaoImpl<T,PK extends Serializable> extends SqlSessionDaoSupport implements TDao<T, Serializable> {
    @Override
    public T addEntity(T entity) {
        getSqlSession().insert(entity.getClass().getName()+".add",entity);
        return entity;
    }
    @Override
    public void delEntity(Class<T> entityClass, Serializable serializable) {
        getSqlSession().delete(entityClass.getName()+".delete",serializable);
    }
    @Override
    public void deleteParamByKey(Class<T> entityClass, Map<String, Object> maps, String operate) {
        getSqlSession().delete(entityClass.getName()+operate,maps);
    }
    @Override
    public void updateEntity(T entity) {
        getSqlSession().update(entity.getClass().getName()+".update",entity);
    }
    @Override
    public void updateParamByKey(Class<T> entityClass, Map<String, Object> maps, String operate) {
        getSqlSession().update(entityClass.getName()+operate,maps);
    }
    public T findById(Class<T> entityClass, Serializable pk) {
        return getSqlSession().selectOne(entityClass.getName()+".queryById",pk);
    }
    @Override
    public T findByParam(Class<T> entityClass, Map<String, Object> maps, String operate) {
        return getSqlSession().selectOne(entityClass.getName()+operate, maps);
    }
    @Override
    public List<T> findAll(Class<T> entityClass) {
        return getSqlSession().selectList(entityClass.getName()+".findAll");
    }
    @Override
    public List<T> findAllByKey(Class<T> entityClass, Map<String, Object> maps, String operate) {
        return getSqlSession().selectList(entityClass.getName()+operate, maps);
    }
    @Override
    public Pager<T> findByPage(Class<T> entityClass, Integer pageNo, Integer pageSize) {
        return findByPage(entityClass, pageNo, pageSize,null);
    }
    @Override
    public Pager<T> findByPage(Class<T> entityClass, Integer pageNo, Integer pageSize, Object obj) {
        Pager<T> pager = new Pager<T>();
        Map<String, Object> maps =new HashMap<String, Object>();
        maps.put("pageNo", pageNo);
        maps.put("pageSize", pageSize);
        if(obj!=null){
            maps.put("condition", obj);
        }
        List<T> pageList = getSqlSession().selectList(entityClass.getName()+".findByPage", maps);
        int totalNum =getTotalNum(entityClass, obj);
        pager.setPageList(pageList);
        pager.setTotalNum(totalNum);
        return pager;
    }
    @Override
    public Pager<T> findByKey(Class<T> entityClass, Map<String, Object> maps, String operate) {
        Pager<T> pager = new Pager<T>();
        List<T> pageList = getSqlSession().selectList(entityClass.getName()+operate, maps);
        int totalNum =getTotalNum(entityClass, maps,operate);
        pager.setPageList(pageList);
        pager.setTotalNum(totalNum);
        return pager;
    }
    @Override
    public int isExist(Class<T> entityClass, Map<String, Object> maps, String operate) {
        int count =0 ;
        count =getSqlSession().selectOne(entityClass.getName()+operate, maps);
        return count;
    }
    /**
     * 分页得到总和
     * @param entityClass
     * @param maps
     * @param operate
     * @return
     */
    private int getTotalNum(Class<T> entityClass,Map<String, Object> maps,String operate){
        int totalNum=0;
        totalNum =getSqlSession().selectOne(entityClass.getName()+operate+"Total",maps);
        return totalNum;
    }
    private int getTotalNum(Class<T> entityClass,Object obj){
        int totalNum=0;
        if(obj!=null){
            totalNum =getSqlSession().selectOne(entityClass.getName()+".findTotal",obj);
        }else{
            totalNum =getSqlSession().selectOne(entityClass.getName()+".findTotal");
        }
        return totalNum;
    }
}
