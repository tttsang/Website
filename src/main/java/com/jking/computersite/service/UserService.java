package com.jking.computersite.service;

import com.jking.computersite.entity.User;

public interface UserService {

    void login(User user);

    void changePassword(String oldPassword, String newPassword);

}
