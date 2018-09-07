package com.javamaster.dao;


import com.javamaster.domain.User;
import com.javamaster.exceptions.DBException;
import com.javamaster.exceptions.UserExistException;
import com.javamaster.sessionfactory.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {
    public User findByName(String name) throws DBException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, name);
            String password = user.getPassword();
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            return user;
        } catch (HibernateException e) {
            throw new DBException();
        }
    }

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
