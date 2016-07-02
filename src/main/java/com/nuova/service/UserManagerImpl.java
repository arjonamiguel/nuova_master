package com.nuova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.UserDAO;
import com.nuova.model.UserDetails;

@Service
@Transactional
public class UserManagerImpl implements UserManager {
    @Autowired
    UserDAO userDAO;

    @Override
    public void edit(UserDetails user) {
        userDAO.edit(user);
    }

    @Override
    public UserDetails findUserByUserName(String userName) {
        return userDAO.findUserByUserName(userName);
    }

    @Override
    public UserDetails findUserByUserId(Integer userId) {
        return userDAO.findUserByUserId(userId);
    }

}
