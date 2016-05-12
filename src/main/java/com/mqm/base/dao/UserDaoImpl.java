package com.mqm.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.mqm.entity.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDaoImpl extends HibernateDao implements UserDaoI {

	public User getUser(Long userId) {
        return (User) getSession().get(User.class, userId);
    }

    public User findUser(String username) {
        Assert.hasText(username);
        String query = "from User u where u.userName = :username";
        return (User) getSession().createQuery(query).setString("username", username).uniqueResult();
    }

    public void createUser(User user) {
        getSession().save( user );
    }

    public List<User> getAllUsers() {
        return getSession().createQuery("from User order by username").list();
    }

    public void deleteUser(Long userId) {
        User user = getUser(userId);
        if( user != null ) {
            getSession().delete(user);
        }
    }

    public void updateUser(User user) {
        getSession().update(user);
    }

}