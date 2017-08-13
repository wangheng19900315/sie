package com.sie.service.impl;

import com.sie.framework.base.GenericDao;
import com.sie.service.bean.PageInfo;
import com.sie.framework.entity.BaseEntity;
import com.sie.service.BaseService;
import com.sie.util.NumberUtil;
import com.sie.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/12.
 */
public class BaseServiceImpl<T extends BaseEntity, PK extends Serializable> implements BaseService<T,PK>{

    private GenericDao<T,PK> baseDao;


    @Autowired
    BaseServiceImpl( GenericDao<T,PK> baseDao){
        this.baseDao = baseDao;
    }

    @Override
    public T get(PK id) {
        return null;
    }

    @Override
    public Integer saveOrUpdate(T entity) {
        return null;
    }

    @Override
    public void delete(PK id) {
        T entity = this.get(id);
        entity.setHdelete(1);
        this.baseDao.updateEntity(entity);
    }

    @Override
    public PageInfo<T> getList(Integer page, Integer rows, Map<String, Object> parameter) {
        //TODO 查询条件没有
        if (!NumberUtil.isSignless(rows)) {
            rows = Integer.MAX_VALUE;
        }

        if (!NumberUtil.isSignless(page)) {
            page = 0;
        }
        Integer records = baseDao.getList_count();
        PageInfo<T> pageBean = new PageInfo<>(records, PageUtil.getPageTotal(records, rows));
        pageBean.setPage(PageUtil.getPageNow(page, pageBean.getTotal()));
        Integer firstResult = PageUtil.getFirstResult(pageBean.getPage(), rows);
        Integer maxResults = PageUtil.getMaxResults(rows);
        List<T> entityList =  baseDao.getList(firstResult, maxResults);

        pageBean.setRows(entityList);
        pageBean.setPage(page);
        pageBean.setRecords(records);
        return pageBean;
    }
}
