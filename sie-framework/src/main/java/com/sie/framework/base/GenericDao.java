package com.sie.framework.base;

import com.sie.framework.entity.BaseEntity;
import com.sie.framework.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Transactional
public interface GenericDao<T extends BaseEntity, PK extends Serializable> {

	public abstract void deleteEntity(T entity);

	public abstract void deleteEntityReal(T entity);

    public abstract void recoverEntity(T entity);

	public abstract T getEntity(PK id);

	public abstract List<T> getList();

	public abstract List<T> getList(int firstResult, int maxResults);

	public abstract int getList_count();

	public List<T> getList(String hql);

//	public abstract void saveEntity(T entity);

    public abstract void createEntity(BaseEntity entity);

    public abstract void updateEntity(BaseEntity entity);

	//TODO 缺少查询条件的接口 获取带查询条件的条数
}