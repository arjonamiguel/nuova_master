package com.nuova.dao;

import com.nuova.model.UserDetails;

public interface UserDAO {

    public void edit(UserDetails user);

    public UserDetails findUserByUserName(String userName);

    public UserDetails findUserByUserId(Integer userId);

}
