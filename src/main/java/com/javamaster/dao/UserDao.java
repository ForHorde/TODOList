package com.javamaster.dao;


import com.javamaster.domain.User;
import com.javamaster.exceptions.ConnectionException;
import com.javamaster.exceptions.DBException;
import com.javamaster.exceptions.UserExistException;
import com.javamaster.sessionfactory.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * Класс для работы с таблицей пользователей через Hibernate
 */
@Repository("userDao")
public class UserDao {

    /**
     * Поиск пользователя по имени
     */
    public User findByName(String name) throws DBException, ConnectionException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, name);
            String password = user.getPassword();
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            return user;
        } catch (HibernateException e) {
            throw new DBException();
        }
    }

    /**
     * Добавление нового пользователя
     */
    public void addUser(User user) throws UserExistException {
       try {
           Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
           Transaction tx1 = session.beginTransaction();
           session.save(user);
           tx1.commit();
       } catch (Exception e) {
          throw new UserExistException();
       }

    }
}
