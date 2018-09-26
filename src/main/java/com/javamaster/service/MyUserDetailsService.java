package com.javamaster.service;

import com.javamaster.dao.UserDao;
import com.javamaster.domain.User;
import com.javamaster.exceptions.ConnectionException;
import com.javamaster.exceptions.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с Spring Security
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    /**
     * Загрузка польозователя по имени
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDao.findByName(username);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
