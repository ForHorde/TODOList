package com.javamaster.service;

import com.javamaster.dao.UserDao;
import com.javamaster.domain.User;
import com.javamaster.exceptions.DBException;
import com.javamaster.exceptions.UserExistException;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();

    @Override
    public void addUser(User user) throws UserExistException {
        userDao.addUser(user);
    }

    @Override
    public User getUser(String name) throws DBException {
       return userDao.findByName(name);
    }
}
