package com.javamaster.dao;

import com.javamaster.domain.Note;
import com.javamaster.exceptions.ConnectionException;
import com.javamaster.exceptions.DBException;
import com.javamaster.sessionfactory.HibernateSessionFactoryUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Класс для работы с таблицей записей через Hibernate
 */
public class NoteDao {
    private static Logger logger = LogManager.getLogger(NoteDao.class);

    /**
     * Метод возвращающий запись по её id
     * @param id - id для нахождения записи в БД
     */
    public Note findById(long id) throws  ConnectionException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Note note = session.get(Note.class, id);
        return note;

    }

    /**
     * Метод для сохранения новой записи
     * @param note
     */
    public void save(Note note) throws DBException, ConnectionException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(note);
            tx1.commit();
        } catch (HibernateException e) {
            logger.info("Введено слишком много символов. Запись не поместилась в БД.");
            throw new DBException();

        }
    }

    /**
     * Метод для обновления записи
     * @param note - изменненая запись
     */
    public void update(Note note) throws DBException, ConnectionException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(note);
            tx1.commit();
        } catch (PersistenceException e) {
            logger.info("Введено слишком много символов. Запись не поместилась в БД.");
            throw new DBException();
        }
    }

    /**
     * Метод для удаления записи
     * @param note - запись для удаления
     */
    public void delete(Note note) throws ConnectionException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(note);
        tx1.commit();

    }

    /**
     * Метод возвращающий список всех записей из БД
     */
    public List<Note> findAll() throws ConnectionException {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Note> notes = session.createQuery("FROM Note").list();
        return notes;


    }

}
