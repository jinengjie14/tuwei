package com.zyl.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zyl.demo.model.Info;
import com.zyl.demo.utils.PageUtil;
/**
 * 笔记Dao
 * @author Zyl
 *
 */
@Repository
@Transactional
public class InfoDao {
	@PersistenceContext
	private EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	public void create(Info info){
		try {
			getSession().save(info);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public Info findById(String id){
		try {
			Info info = getSession().get(Info.class, id);
			return info;
		} catch (Exception e) {
			throw e;
		}
	} 
	
	public void delete(Info info){
		try {
			getSession().delete(info);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void meger(Info info){
		try {
			getSession().merge(info);
		} catch (Exception e) {
			throw e;
		}
	}
	public PageUtil<Info> findByAll(String keyword,int pagesize,int page){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Info.class);
			if (keyword != null) {
				Disjunction dis = Restrictions.disjunction();
				dis.add(Property.forName("title").like(keyword, MatchMode.ANYWHERE));
				dis.add(Property.forName("markdown").like(keyword, MatchMode.ANYWHERE));
				dc.add(dis);
			}
			dc.addOrder(Order.desc("ctime"));
			Criteria criteria = dc.getExecutableCriteria(getSession());
			Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();//获取总页数
			long totalCount = 0;
			try {
				totalCount = (Long) object;
			} catch (Exception e) {
			}
			PageUtil<Info> ps = new PageUtil<Info>((int) totalCount, pagesize, page);
			criteria.setProjection(null);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Info> items = criteria.setFirstResult(ps.getStartindex()).setMaxResults(pagesize).list();
			ps.setItems(items);
			return ps;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
