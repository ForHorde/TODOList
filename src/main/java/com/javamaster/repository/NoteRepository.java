package com.javamaster.repository;

import com.javamaster.domain.Note;

import java.util.List;

public interface NoteRepository {
    void save(Note note);
    void delete(Note note);
    void change(Integer id, Note newNote);
    List<Note> getAll();
    Note getById(Integer id);
}
