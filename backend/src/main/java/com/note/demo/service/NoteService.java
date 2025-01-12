package com.note.demo.service;

import com.note.demo.mapper.NoteMapper;
import com.note.demo.model.DTO.NoteReqDTO;
import com.note.demo.model.DTO.NoteResponseDTO;
import com.note.demo.model.Entity.Note;
import com.note.demo.repository.INoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {

    @Autowired
    private INoteRepository noteRepository;

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public List<NoteResponseDTO> getNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream()
                .map(noteMapper::toResponseDTO)
                .toList(); // Transformación usando Mapper
    }

    @Override
    public NoteResponseDTO saveNote(NoteReqDTO noteReqDTO) {
        Note note = noteMapper.toEntity(noteReqDTO);
        Note savedNote = noteRepository.save(note);
        return noteMapper.toResponseDTO(savedNote);
    }

    @Override
    public NoteResponseDTO findNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note with ID " + id + " not found"));
        return noteMapper.toResponseDTO(note);
    }

    @Override
    public NoteResponseDTO updateNote(Long id, NoteReqDTO noteReqDTO) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note with ID " + id + " not found"));

        existingNote.setTitle(noteReqDTO.getTitle());
        existingNote.setContent(noteReqDTO.getContent());
        existingNote.setArchived(noteReqDTO.getArchived());
        existingNote.setCategories(noteReqDTO.getCategories());

        Note updatedNote = noteRepository.save(existingNote);
        return noteMapper.toResponseDTO(updatedNote);
    }

    @Override
    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new EntityNotFoundException("Note with ID " + id + " not found");
        }
        noteRepository.deleteById(id);
    }

    @Override
    public void archiveNote(Long id, boolean archived) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note with ID " + id + " not found"));
        note.setArchived(archived);
        noteRepository.save(note);
    }

    @Override
    public List<NoteResponseDTO> getNotesByCategory(String category) {
        List<Note> notes = noteRepository.findByCategory(category);
        return notes.stream()
                .map(noteMapper::toResponseDTO)
                .toList();
    }
}
