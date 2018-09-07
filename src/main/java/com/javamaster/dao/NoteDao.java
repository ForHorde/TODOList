package com.javamaster.dao;

import com.javamaster.domain.Note;
import com.javamaster.exceptions.DBException;
import com.javamaster.sessionfactory.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NoteDao {
    public Note findById(int id) throws DBException {
       try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
           Note note = session.get(Note.class, id);
           return note;
       } catch (HibernateException e) {
           throw new DBException();
       }
    }

    public void save(Note note) throws DBException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(note);
            tx1.commit();
        } catch (HibernateException e) {
            throw new DBException();
        }
    }

    public void update(Note note) throws DBException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(note);
            tx1.commit();
        } catch (HibernateException e) {
            throw new DBException();
        }
    }

    public void delete(Note note) throws DBException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(note);
            tx1.commit();
        } catch (HibernateException e) {
            throw new DBException();
        }
    }



    public List<Note> findAll() throws DBException {

        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            List<Note> notes = session.createQuery("FROM Note").list();
            return notes;
        } catch (HibernateException e) {
            throw new DBException();
        }

    }
}
