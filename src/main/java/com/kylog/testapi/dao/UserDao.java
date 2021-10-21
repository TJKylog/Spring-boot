package com.kylog.testapi.dao;

import com.kylog.testapi.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    void delete(Long id);

    void register(User user);

    User login(User user);
}
