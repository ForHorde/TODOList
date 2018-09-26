package com.javamaster.sessionfactory;

import com.javamaster.domain.Note;
import com.javamaster.domain.User;
import com.javamaster.exceptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Класс для работы с Hibernate
 */
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private final static Logger logger = LogManager.getLogger(HibernateSessionFactoryUtil.class);

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() throws ConnectionException {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Note.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                logger.error("Ошибка подключения к базе данных", e);
                throw new ConnectionException();
            }
        }
        return sessionFactory;
    }
}