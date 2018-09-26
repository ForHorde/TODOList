package com.javamaster.controller;


import com.javamaster.domain.Note;
import com.javamaster.exceptions.ConnectionException;
import com.javamaster.exceptions.DBException;
import com.javamaster.service.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * Контроллер для управления записями
 */
@Controller
public class NoteController {

    private NoteService noteService = new NoteRestService();
    private UserService userService = new UserServiceImpl();

    /**
     * Метод для вывода всех записей
     * @param model - MVC модель
     * @return notes.jsp - страница со всеми записями.
     * @return exceptionPage - страница в случае ошибки подключения к БД.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getNotePage(Model model) {
        List<Note> notes = null;
        try {
            notes = noteService.getAllByUser(SecurityContextHolder.getContext().getAuthentication().getName());

        } catch (DBException e) {
            return "exceptionPage";
        } catch (ConnectionException e) {
            return "exceptionPage";
        } catch (Exception e) {
            return "connectionExp";
        }
        model.addAttribute("noteList", notes);
        return "notes";
    }

    /**
     * Метод для добавления новой записи
     * @return addNewNote.jsp - страница для добавления новой записи
     */
    @RequestMapping(value = "/add-new-note", method = RequestMethod.GET)
    public String addNewNote() {
        return "addNewNote";
    }

    /**
     * Метод для изменения существующей записи
     * @param model - модель для добавления записи для редактирования и её картинки
     * @param id - id записи для изменения
     * @return editNote.jsp - страница для изменения записи
     * @return exceptionPage - страница в случае ошибки подключения к БД
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editNote(Model model, @PathVariable Integer id) {
        Note note = null;
        try {
            note = noteService.getById(id);
            model.addAttribute("pic", pictureEncoder(note.getPicture()));
        } catch (DBException e) {
            return "exceptionPage";
        } catch (ConnectionException e) {
            return "exceptionPage";
        } catch (Exception e) {
            return "connectionExp";
        }
        model.addAttribute("note", note);
        return "editNote";
    }

    /**
     * Метод для удаления картинки
     * @param id - id записи, в которой пользователь удаляет картинку
     * @return "redirect:/edit/" + id - возвращает на страницу редактирования записи уже без картинки
     */
    @RequestMapping(value = "delete/picture/{id}")
    public String deletePic(@PathVariable Integer id) {
        try {
            Note note = noteService.getById(id);
            note.setPicture(null);
            noteService.update(note);
        } catch (DBException e) {
            return "exceptionPage";
        } catch (ConnectionException e) {
            return "exceptionPage";
        } catch (Exception e) {
            return "connectionExp";
        }
        return "redirect:/edit/" + id;
    }

    /**
     * Метод сохранения измененной записи
     * @param text - измененный текст записи
     * @param title - измененный заголовок
     * @param picture - картинка, прикрепленная к записи
     * @param id - id записи
     * @param model - MVC модель для добавления записи с слишком большим текстом
     * @return "redirect:/show/" + id - перенаправляет в метод отображения текущей записи.
     * В случае слишком длинного текста добавляет в модель этот текст и перенаправляет на страницу редактирования записи editNote.jsp
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String editNote(@RequestParam(value = "text") String text, @RequestParam(value = "title") String title, @RequestParam(name = "picture") CommonsMultipartFile[] picture, @PathVariable Integer id, Model model) {
        Note note = null;
        try {
            note = noteService.getById(id);
            note.setText(text);
            note.setTitle(title);

            if (picture != null && picture.length > 0) {
                for (CommonsMultipartFile aFile : picture) {
                    if (!aFile.isEmpty())
                        note.setPicture(aFile.getBytes());
                }
            }
            noteService.update(note);
        } catch (DBException e) {
            model.addAttribute("enote", note);
            return "editNote";
        } catch (ConnectionException e) {
            return "exceptionPage";
        }  catch (Exception e) {
            return "connectionExp";
        }

        return "redirect:/show/" + id;
    }

    /**
     * Метод для сохранения новой записи
     * @param text - текст новой записи
     * @param model - модель для добавления текста в случай слишком длинной записи
     * @param title - заголовок записи
     * @param picture - картинка, прикрепленная к записи
     * @return redirect:/ - Перенаправляет на страницу всех записей
     * @return exceptionPage - страница в случае ошибки подключения к БД.
     * В случае слишком длинного текста добавляет в модель этот текст и перенаправляет на страницу добавления записи addNewNote.jsp
     */
    @RequestMapping(value = "/add-new-note", method = RequestMethod.POST)
    public String addNewNote(@RequestParam(value = "text") String text, @RequestParam(value = "title") String title, @RequestParam(name = "picture") CommonsMultipartFile[] picture, Model model) {
        Note note = new Note();
        note.setText(text);
        note.setTitle(title);
        note.setColor("#ffffff");
        if (picture != null && picture.length > 0) {
            for (CommonsMultipartFile aFile : picture) {
                if (!aFile.isEmpty())
                    note.setPicture(aFile.getBytes());
            }
        }
        try {
            note.setUser(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
            Integer ord = noteService.getAllByUser(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUsername()).size()+1;
            note.setOrd(ord);
            noteService.save(note);
        } catch (DBException e) {
            model.addAttribute("enote", note);
            return "addNewNote";
        } catch (ConnectionException e) {
            return "exceptionPage";
        } catch (Exception e) {
           return "connectionExp";
        }


        return "redirect:/";
    }

    /**
     * Метод для удаления записи
     * @param id - id записи для удаления
     * @return redirect:/ - перенаправление на главную страницу
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id) {
        Note note = null;
        try {
            note = noteService.getById(id);
            noteService.delete(note);
        } catch (DBException e) {
            return "exceptionPage";
        } catch (ConnectionException e) {
            return "exceptionPage";
        } catch (Exception e) {
            return "connectionExp";
        }

        return "redirect:/";
    }

    /**
     * Метод для отображения записи
     * @param model - модель для добавления записи
     * @param id - id записи
     * @return note.jsp - страница отображения записи
     */
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String showNote(Model model, @PathVariable Integer id) {
        Note note = null;
        try {
            note = noteService.getById(id);
            model.addAttribute("pic", pictureEncoder(note.getPicture()));
        } catch (DBException | ConnectionException e) {
            return "exceptionPage";
        } catch (Exception e) {
            return "connectionExp";
        }
        model.addAttribute("note", note);

        return "note";
    }

    /**
     * Метод для изменения порядка записей согласно переданному списку
     * @param text - сериализованный список записей
     */
    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    @ResponseBody
    public String sort(@RequestParam String text) throws Exception {
        String[] ids = text.split("&");

        for (int i=1; i<ids.length; i++) {
            int id = Integer.parseInt(ids[i].split("color")[0]);
            String color = ids[i].split("color")[1];
            Note note = noteService.getById(id);
            note.setOrd(i);
            note.setColor(color);
            noteService.update(note);
        }


        return "";
    }

    /**
     * Преобразует массив байтов в картинку
     * @param picture
     * @return pic - строка для отображения картинки на странице
     */
    public String pictureEncoder(byte[] picture) {
        if (picture != null) {
           //byte[] encoded = Base64.getEncoder().encode(picture);
            String pic = "data:image/jpeg;base64," + new String(picture);
            return pic;
        }
        return null;
    }


}
