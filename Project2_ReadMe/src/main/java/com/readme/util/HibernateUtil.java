package com.readme.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	private Session session;
	
	public Session getSession() {
		if(session == null){
			session = sf.openSession();
		}
		return session;
	}
	
	public void closeFactory() {
		session.close();
		sf.close();
	}
	
	public String checkSession() {
		if(session == null) {
			return "no session open";
		}
		return "session is open";
	}
}
