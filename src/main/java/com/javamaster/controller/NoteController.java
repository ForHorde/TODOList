package com.javamaster.controller;


import com.javamaster.config.SecurityConfig;
import com.javamaster.domain.Note;
import com.javamaster.domain.User;
import com.javamaster.exceptions.DBException;
import com.javamaster.service.NoteService;
import com.javamaster.service.NoteServiceImpl;
import com.javamaster.service.UserService;
import com.javamaster.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoteController {

    private NoteService noteService = new NoteServiceImpl();
    private UserService userService = new UserServiceImpl();

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getNotePage(Model model) {
        List<Note> notes = null;
        try {
            notes = noteService.getAllByUser(SecurityContextHolder.getContext().getAuthentication().getName());

        } catch (DBException e) {
            return "exceptionPage";
        }
        model.addAttribute("noteList", notes);
        return "note";
    }

    @RequestMapping(value = "/add-new-note", method=RequestMethod.GET)
    public String addNewNote() {
        return "addNewNote";
    }

    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    public String editNote(Model model, @PathVariable Integer id) {
        Note note = null;
        try {
            note = noteService.getById(id);
        } catch (DBException e) {
            return "exceptionPage";
        }
        model.addAttribute("note", note);
        return "editNote";
    }

    @RequestMapping(value="edit/{id}", method=RequestMethod.POST)
    public String editNote(@RequestParam(value = "note") String text, @PathVariable Integer id) {
        Note note = null;
        try {
            note = noteService.getById(id);
            note.setText(text);
            noteService.update(note);
        } catch (DBException e) {
            return "exceptionPage";
        }

        return "redirect:/";
    }

    @RequestMapping(value="/add-new-note", method=RequestMethod.POST)
    public String addNewNote(@RequestParam(value="note") String text) {
        Note note = new Note();
        note.setText(text);
        try {
            note.setUser(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        } catch (DBException e) {
            return "exceptionPage";
        }
        try {
            noteService.save(note);
        } catch (DBException e) {
            return "exceptionPage";
        }
        return "redirect:/";
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id) {
        Note note = null;
        try {
            note = noteService.getById(id);
            noteService.delete(note);
        } catch (DBException e) {
           return  "exceptionPage";
        }

        return "redirect:/";
    }




}
