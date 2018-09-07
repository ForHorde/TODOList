package com.javamaster.service;

import com.javamaster.domain.Note;
import com.javamaster.exceptions.DBException;

import java.util.List;

public interface NoteService {
    void save(Note note) throws DBException;

    void delete(Note note) throws DBException;

    void update(Note note) throws DBException;

    List<Note> getAll() throws DBException;

    List<Note> getAllByUser(String name) throws DBException;

    Note getById(Integer id) throws DBException;
}
