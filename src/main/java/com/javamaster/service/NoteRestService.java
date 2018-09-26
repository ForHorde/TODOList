package com.javamaster.service;

import com.google.gson.Gson;
import com.javamaster.domain.Note;
import com.javamaster.domain.User;
import com.javamaster.exceptions.ConnectionException;
import com.javamaster.exceptions.DBException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NoteRestService implements NoteService {
    @Override
    public void save(Note note) throws DBException, ConnectionException, IOException {
        String url = "http://localhost:8080/jaxrs-1.0-SNAPSHOT/note-service/notes/add-new-note";
        postHTML(url, note);
    }

    @Override
    public void delete(Note note) throws Exception {
        getHTML("http://localhost:8080/jaxrs-1.0-SNAPSHOT/note-service/notes/delete/"+note.getId());
    }

    @Override
    public void update(Note note) throws DBException, ConnectionException, IOException {
        String url = "http://localhost:8080/jaxrs-1.0-SNAPSHOT/note-service/notes/update";
        postHTML(url, note);
    }

    @Override
    public List<Note> getAll() throws DBException, ConnectionException {
        return null;
    }

    @Override
    public List<Note> getAllByUser(String name) throws Exception {
        JSONArray json = (JSONArray) new JSONParser().parse(getHTML("http://localhost:8080/jaxrs-1.0-SNAPSHOT/note-service/notes"));
        List<Note> notes = new ArrayList<>();
        for (Object o :
                json) {
            JSONObject jsonObject = (JSONObject) o;
            if (((JSONObject)jsonObject.get("user")).get("username").equals(name)) {
               // Gson gson = new Gson();
               // Note note = gson.fromJson(String.valueOf(jsonObject), Note.class);
                Note note = jsonToNote(jsonObject);
                notes.add(note);
            }

        }
        return notes;
    }

    @Override
    public Note getById(Integer id) throws Exception {
        JSONObject json = (JSONObject) new JSONParser().parse(getHTML("http://localhost:8080/jaxrs-1.0-SNAPSHOT/note-service/notes/"+id));
        Note note = jsonToNote(json);
        return note;
    }

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static void postHTML(String urlToRead, Object o) throws IOException {
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");


        Gson gson = new Gson();
        String json = gson.toJson(o, Note.class);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(json);
        wr.flush();
        conn.getResponseMessage();
    }

    public static Note jsonToNote(JSONObject json) {
        Note note = new Note();
        note.setId((Long) json.get("id"));
        note.setTitle((String) json.get("title"));
        note.setText((String) json.get("text"));
        note.setColor((String) json.get("color"));
        String s = (String) json.get("picture");
        if (s!=null) {
            note.setPicture(s.getBytes());
        } else {
            note.setPicture(null);
        }
        JSONObject user = (JSONObject) json.get("user");
        note.setUser(jsonToUser(user));
        return note;
    }
    public static User jsonToUser(JSONObject json) {
        User user = new User();
        user.setUsername((String) json.get("username"));
        user.setPassword((String) json.get("password"));
        return user;
    }
}
