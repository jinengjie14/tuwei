package com.zyl.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zyl.demo.model.User;

/**
 * 用户Dao
 * @author Zyl
 *
 */
@Repository
@Transactional
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	public void create(User user){
		try {
			getSession().save(user);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	public List<User> findByAccount(String account) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("account", account));
		Criteria criteria = dc.getExecutableCriteria(getSession());
		List<User> user = criteria.list();
		return user;
	}
}
