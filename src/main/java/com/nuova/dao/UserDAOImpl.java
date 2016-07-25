package com.nuova.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.UserDetails;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void edit(UserDetails user) {
        this.sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public UserDetails findUserByUserName(String userName) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM UserDetails u WHERE  u.username= :userName")
                .setString("userName", userName);
        List<UserDetails> result = query.list();

        return result == null ? null : result.get(0);
    }

    @Override
    public UserDetails findUserByUserId(Integer userId) {
        return (UserDetails) this.sessionFactory.getCurrentSession().get(UserDetails.class,
                userId);
    }

}
