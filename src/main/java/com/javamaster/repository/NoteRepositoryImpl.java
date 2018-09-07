package com.javamaster.repository;

import com.javamaster.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteRepositoryImpl implements NoteRepository {
    private List<Note> notes = new ArrayList<Note>();

    public NoteRepositoryImpl() {
        Note note1 = new Note();
        note1.setId(1);
        note1.setText("aaaaaaaaa");
        Note note2 = new Note();
        note2.setId(2);
        note2.setText("bbbbbbbbbbbb");
        notes.add(note1);
        notes.add(note2);
    }

    @Override
    public void save(Note note) {
        notes.add(note);
    }

    @Override
    public void delete(Note note) {
        notes.remove(note);
    }

    @Override
    public void change(Integer id, Note newNote) {
        notes.set(id, newNote);
    }


    @Override
    public List<Note> getAll() {
        return notes;
    }

    @Override
    public Note getById(Integer id) {
        for (Note note: notes
             ) {
            if (note.getId()==id)
                return note;
        }
        return null;
    }
}
