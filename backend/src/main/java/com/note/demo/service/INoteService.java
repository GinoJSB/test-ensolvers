package com.note.demo.service;

import com.note.demo.model.DTO.NoteReqDTO;
import com.note.demo.model.DTO.NoteResponseDTO;

import java.util.List;

public interface INoteService {

    List<NoteResponseDTO> getNotes();

    NoteResponseDTO saveNote(NoteReqDTO noteReqDTO);

    NoteResponseDTO findNoteById(Long id);

    NoteResponseDTO updateNote(Long id, NoteReqDTO noteReqDTO);

    void deleteNote(Long id);

    void archiveNote(Long id, boolean archived);

    List<NoteResponseDTO> getNotesByCategory(String category);
}
