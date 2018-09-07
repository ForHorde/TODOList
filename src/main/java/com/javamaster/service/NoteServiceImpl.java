package com.javamaster.service;

import com.javamaster.dao.NoteDao;
import com.javamaster.domain.Note;
import com.javamaster.exceptions.DBException;
import com.javamaster.repository.NoteRepository;
import com.javamaster.repository.NoteRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class NoteServiceImpl implements NoteService {

    private NoteDao noteDao = new NoteDao();

    @Override
    public void update(Note note) throws DBException {
        noteDao.update(note);
    }

    @Override
    public void save(Note note) throws DBException {
        noteDao.save(note);
    }

    @Override
    public void delete(Note note) throws DBException {
       noteDao.delete(note);
    }

    @Override
    public List<Note> getAll() throws DBException {
        return noteDao.findAll();
    }

    @Override
    public List<Note> getAllByUser(String name) throws DBException {
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
    public Note getById(Integer id) throws DBException {
     return   noteDao.findById(id);
    }
}
