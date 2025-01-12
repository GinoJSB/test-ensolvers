package com.note.demo.service;

import com.note.demo.model.DTO.NoteReqDTO;
import com.note.demo.model.DTO.NoteResponseDTO;
import com.note.demo.model.Entity.Note;

import java.util.List;

public interface INoteService {


    List<Note> getNotes();


    void saveNote(Note note);


    void deleteNote(Long id);


    Note findNoteById(Long id);


    NoteResponseDTO updateNote(Long id, NoteReqDTO noteReqDTO);


    void archiveNote(Long id, boolean archived);


    List<Note> getNotesByCategory(String category);
}
