package com.taoyb.simon.common.base;
import com.taoyb.simon.common.utils.Pager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
public interface BaseDao<T,PK extends Serializable> {
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:46
     * @Des:保存对象
     */
    public T addEntity(T entity);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:47
     * @Des:删除对象
     */
    public void delEntity(PK pk);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:58
     * @Des:通过某个参数删除对象
     */
    public void deleteParamByKey(Map<String, Object> maps, String operate);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:48
     * @Des:修改对象
     */
    public void updateEntity(T entity);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:59
     * @Des:根据某个参数修改对象
     */
    public void updateParamByKey(Map<String, Object> maps, String operate);
    /**
     * @Author: TYB
     * @Date: 2016-12-06 下午 3:19
     * @Des:查询单个对象
     */
    public T findById(PK pk);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:59
     * @Des:通过某个参数查询对象
     */
    public T findByParam(Map<String, Object> maps, String operate);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:49
     * @Des:对象集合查询
     */
    public List<T> findAll();
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:49
     * @Des:带参数对象查询
     */
    public List<T> findAllByKey(Map<String, Object> maps, String operate);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:55
     * @Des:分页查询
     */
    public Pager<T> findByPage(Integer pageNo, Integer pageSize);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:55
     * @Des:根据一个条件查询分页
     */
    public Pager<T> findByPage(Integer pageNo,Integer pageSize,Object obj);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:56
     * @Des:多条件的分页查询
     */
    public Pager<T> findByKey(Map<String, Object> maps,String operate);
    /**
     * @Author: TYB
     * @Date: 2016-12-07 下午 1:57
     * @Des:判断数据是否存在
     */
    public int isExist(Map<String, Object> maps,String operate);
}
