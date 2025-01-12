package com.note.demo.controller;

import com.note.demo.model.DTO.NoteReqDTO;
import com.note.demo.model.DTO.NoteResponseDTO;
import com.note.demo.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private INoteService noteService;

    @GetMapping
    public List<NoteResponseDTO> getAllNotes() {
        return noteService.getNotes();
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(@RequestBody NoteReqDTO noteReqDTO) {
        NoteResponseDTO savedNote = noteService.saveNote(noteReqDTO);
        return ResponseEntity.ok(savedNote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.findNoteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable Long id, @RequestBody NoteReqDTO noteReqDTO) {
        return ResponseEntity.ok(noteService.updateNote(id, noteReqDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
