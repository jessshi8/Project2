package com.readme.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.readme.model.User;
import com.readme.util.HibernateUtil;

public class UserDao {
	private HibernateUtil hUtil;
	
	public UserDao() {
		// TODO Auto-generated constructor stub
	}

	public UserDao(HibernateUtil hUtil) {
		super();
		this.hUtil = hUtil;
	}
	
	public void insert(User u) {
		Session session = hUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(u);
		tx.commit();
	}
	
	public void update(User u) {
		Session session = hUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(u);
		tx.commit();
	}
	
	public void delete(User u) {
		Session session = hUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(u);
		tx.commit();
	}
	
	public User getUserByUsername(String username) {
		Session session = hUtil.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("username"), username));
		List<User> uList = session.createQuery(query).getResultList();
		
		if (uList.size() != 0) {
			return uList.get(0);
		}
		return null;
	}
}
