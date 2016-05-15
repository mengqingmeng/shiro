package com.mqm.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mqm.dao.UserDaoI;
import com.mqm.entity.User;

@Service
public class UserServiceImpl implements UserServiceI{
	 	private UserDaoI userDAO;

	    @Autowired
	    public void setUserDAO(UserDaoI userDAO) {
	        this.userDAO = userDAO;
	    }

	    public User getCurrentUser() {
	        final Long currentUserId = (Long) SecurityUtils.getSubject().getPrincipal();
	        if( currentUserId != null ) {
	            return getUser(currentUserId);
	        } else {
	            return null;
	        }
	    }

	    public void createUser(String username, String email, String password) {
	        User user = new User();
	        user.setUserName(username);
	        user.setEmail(email);
	        user.setPassword( new Sha256Hash(password).toHex() );
	        userDAO.createUser( user );
	    }

	    public List<User> getAllUsers() {
	        return userDAO.getAllUsers();
	    }

	    public User getUser(Long userId) {
	        return userDAO.getUser(userId);
	    }

	    public void deleteUser(Long userId) {
	        userDAO.deleteUser( userId );
	    }

	    public void updateUser(User user) {
	        userDAO.updateUser( user );
	    }

		@Override
		public User findUser(String userName) {
			return userDAO.findUser(userName);
		}
	
}
