package com.javamaster.service;

import com.javamaster.domain.Note;
import com.javamaster.exceptions.ConnectionException;
import com.javamaster.exceptions.DBException;

import java.io.IOException;
import java.util.List;

public interface NoteService {
    void save(Note note) throws DBException, ConnectionException, IOException;

    void delete(Note note) throws Exception;

    void update(Note note) throws DBException, ConnectionException, IOException;

    List<Note> getAll() throws DBException, ConnectionException;

    List<Note> getAllByUser(String name) throws Exception;

    Note getById(Integer id) throws Exception;
}
