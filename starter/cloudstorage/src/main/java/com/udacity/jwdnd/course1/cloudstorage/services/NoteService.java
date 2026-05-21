package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void addNote(Note note) {
        noteMapper.insert(note);
    }

    public void updateNote(Note note) {
        noteMapper.update(note);
    }

    public void deleteNote(int noteId) {
        noteMapper.delete(noteId);
    }

    public List<Note> getNotes(int userId) {
        return noteMapper.getNotes(userId);
    }
}
