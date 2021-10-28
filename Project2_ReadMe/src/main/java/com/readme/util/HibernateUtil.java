package com.readme.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.readme.dao.UserDao;

public class HibernateUtil {
	public static final Logger log = Logger.getLogger(HibernateUtil.class);
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	private Session session;
	
	public Session getSession() {
		log.trace("HibernateUtil getSession() was invoked.");
		if(session == null){
			session = sf.openSession();
		}
		return session;
	}
	
	public void closeFactory() {
		log.trace("HibernateUtil closeFactory() was invoked.");
		session.close();
		sf.close();
	}
	
	public String checkSession() {
		log.trace("HibernateUtil checkSession() was invoked.");
		if(session == null) {
			return "no session open";
		}
		return "session is open";
	}
}
