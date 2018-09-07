package com.javamaster.service;

import com.javamaster.domain.User;
import com.javamaster.exceptions.DBException;
import com.javamaster.exceptions.UserExistException;

import java.util.List;

public interface UserService {


    void addUser(User user) throws UserExistException;

    User getUser(String name) throws DBException;

}
