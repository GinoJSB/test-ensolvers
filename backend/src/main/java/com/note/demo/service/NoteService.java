package com.note.demo.service;

import com.note.demo.model.DTO.NoteReqDTO;
import com.note.demo.model.DTO.NoteResponseDTO;
import com.note.demo.model.Entity.Note;
import com.note.demo.repository.INoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    @Autowired
    private INoteRepository noteRepository;

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note findNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note with ID " + id + " not found"));
    }

    @Override
    public NoteResponseDTO updateNote(Long id, NoteReqDTO noteReqDTO) {
        Note note = findNoteById(id);

        note.setTitle(noteReqDTO.getTitle());
        note.setContent(noteReqDTO.getContent());
        note.setArchived(noteReqDTO.getArchived());
        note.setCategories(noteReqDTO.getCategories());

        noteRepository.save(note);

        return NoteResponseDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .archived(note.isArchived())
                .categories(note.getCategories())
                .build();
    }

    @Override
    public void archiveNote(Long id, boolean archived) {
        Note note = findNoteById(id);
        note.setArchived(archived);
        saveNote(note);
    }

    @Override
    public List<Note> getNotesByCategory(String category) {
        return noteRepository.findAll().stream()
                .filter(note -> note.getCategories() != null && note.getCategories().contains(category))
                .collect(Collectors.toList());
    }
}
