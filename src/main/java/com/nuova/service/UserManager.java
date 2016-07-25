package com.nuova.service;

import com.nuova.model.UserDetails;

public interface UserManager {

    public void edit(UserDetails user);

    public UserDetails findUserByUserId(Integer userId);

    public UserDetails findUserByUserName(String userName);

}
