package com.sie.framework.base;

import com.sie.framework.entity.BaseEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.framework.util.SessionUtil;
import com.sie.util.NumberUtil;
import com.sie.util.PageUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class GenericDaoImpl<T extends BaseEntity, PK extends Serializable> implements GenericDao<T, PK> {

	@Autowired
	protected SessionFactory sessionFactory;

	private Class<T> clazz;

	public GenericDaoImpl() {
		clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void deleteEntity(T entity) {
		BaseEntity e = (BaseEntity) entity;
        e.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		e.setHdelete(1);
		e.setModifyUserId(SessionUtil.getUserId());
		e.setHversion(e.getHversion()+1);
		this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
		this.sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteEntityReal(T entity) {
		this.sessionFactory.getCurrentSession().delete(entity);
		this.sessionFactory.getCurrentSession().flush();
	}

    @Override
    public void recoverEntity(T entity) {
		BaseEntity e = (BaseEntity) entity;
        e.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        e.setHdelete(0);
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
        this.sessionFactory.getCurrentSession().flush();
    }



    @Override
	public T getEntity(PK id) {
		if (id != null) {
			return (T) this.sessionFactory.getCurrentSession().get(clazz, id);
		} else {
			return null;
		}
	}

	@Override
	public List<T> getList() {
		return this.sessionFactory.getCurrentSession().createQuery("from " + clazz.getName()  + " where hdelete=0 ").list();
	}

	@Override
	public List<T> getList(int firstResult, int maxResults) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from " + clazz.getName() +" where hdelete=0 order by id desc");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public int getCount() {
		Query query = this.sessionFactory.getCurrentSession().createQuery("select count(entity) from " + clazz.getName() + " entity where hdelete=0 order by id desc");
		Long count = (Long)query.uniqueResult();
		return count.intValue();
	}


	public List<T> getList(String hql){
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<T> getList(String hql, int firstResult, int maxResults){
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public int getList_count(String hql) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		Long count = (Long)query.uniqueResult();
		return count.intValue();
	}


    @Override
    public void createEntity(BaseEntity entity) {
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		entity.setCreateUserId(SessionUtil.getUserId());
        this.sessionFactory.getCurrentSession().persist(entity);
		this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateEntity(BaseEntity entity) {
        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		entity.setModifyUserId(SessionUtil.getUserId());
		entity.setHversion(entity.getHversion()+1);
        this.sessionFactory.getCurrentSession().update(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

	@Override
	public Integer updateByHql(String hql) {
		Query queryupdate=this.getSessionFactory().getCurrentSession().createQuery(hql);
		int ret=queryupdate.executeUpdate();
		this.sessionFactory.getCurrentSession().flush();
		return ret;
	}

	@Override
	public List<T> getList(List<HqlOperateVo> hqlOperateVos, int firstResult, int maxResults) {
		String hql = "select distinct entity  from " + clazz.getName() +" entity";
		hql = this.getHql(hql, hqlOperateVos);
		return this.getList(hql, firstResult, maxResults);
	}

	@Override
	public List<T> getList(List<HqlOperateVo> hqlOperateVos) {
		String hql = "from " + clazz.getName() + " entity  "  ;
		hql = this.getHql(hql, hqlOperateVos);
		return this.getList(hql);
	}

	@Override
	public Integer getCount( List<HqlOperateVo> hqlOperateVos) {
		String hql = "select count(entity) from " + clazz.getName() + " entity  ";
		hql = this.getHql(hql, hqlOperateVos);
		return this.getList_count(hql);
	}

	private String  getHql(String hql, List<HqlOperateVo> hqlOperateVos){
		if(hqlOperateVos != null && hqlOperateVos.size() > 0){
			for(HqlOperateVo vo:hqlOperateVos){
				if("join".equals(vo.getOperate())){
					hql += vo.getName();
				}
				if(StringUtils.isEmpty(vo.getValue())){
					continue;
				}


				if(hql.indexOf("where") > -1){
					hql += " and ";
				}else{
					hql += " where ";
				}

				if("like".equals(vo.getOperate())){
					hql += " "+vo.getName()+" like '%"+vo.getValue()+"%'";
				}else{
					hql += "  "+vo.getName()+vo.getOperate()+" '"+vo.getValue()+"'";
				}
			}
		}

		if(hql.indexOf("where") > -1){
			hql += " and entity.hdelete=0 order by entity.id desc";
		}else{
			hql += " where entity.hdelete=0 order by entity.id desc";
		}


		return hql;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
