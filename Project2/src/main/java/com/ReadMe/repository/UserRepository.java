package com.ReadMe.repository;// basically substitute the DAO with Repository they are interchangeable
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ReadMe.model.Users;

@Repository("userRepo")// this is a stereotype annotation it doesn't come from spring ORM

@Transactional
public class UserRepository {
	
	private SessionFactory sesFact;
	
	public  UserRepository () {

}
    @Autowired
	public UserRepository(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
     
	public void insert(Users user) {
		
		sesFact.getCurrentSession().save(user);
			
	}
	
    public List<Users> selectAll(){
    	return sesFact.getCurrentSession().createQuery("from User", Users.class).list();
    }
    
    public Users selectByUsername(String username) {	
        List<Users> uList = sesFact.getCurrentSession().createQuery("from User where username='"+ username + "'", Users.class).list();
        if(uList.size()==0) {
            return null;
        }
        return uList.get(0); 	
    }
}
    
