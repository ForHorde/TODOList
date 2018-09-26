package com.javamaster.service;

import com.javamaster.dao.NoteDao;
import com.javamaster.domain.Note;
import com.javamaster.exceptions.ConnectionException;
import com.javamaster.exceptions.DBException;


import java.util.ArrayList;
import java.util.List;

public class NoteServiceImpl implements NoteService {

    private NoteDao noteDao = new NoteDao();

    @Override
    public void update(Note note) throws DBException, ConnectionException {
        noteDao.update(note);
    }

    @Override
    public void save(Note note) throws DBException, ConnectionException {
        noteDao.save(note);
    }

    @Override
    public void delete(Note note) throws DBException, ConnectionException {
       noteDao.delete(note);
    }

    @Override
    public List<Note> getAll() throws DBException, ConnectionException {
        return noteDao.findAll();
    }

    @Override
    public List<Note> getAllByUser(String name) throws DBException, ConnectionException {
       List<Note> notes = noteDao.findAll();
       List<Note> userNotes = new ArrayList<>();
        for (Note note :
                notes) {
            if (note.getUser().getUsername().equals(name))
                userNotes.add(note);
        }

        return userNotes;
    }

    @Override
    public Note getById(Integer id) throws DBException, ConnectionException {
     return   noteDao.findById(id);
    }
}
