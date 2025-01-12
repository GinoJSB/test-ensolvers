package com.note.demo.mapper;

import com.note.demo.model.Entity.Note;
import com.note.demo.model.DTO.NoteReqDTO;
import com.note.demo.model.DTO.NoteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);


    NoteResponseDTO toResponseDTO(Note note);


    @Mapping(target = "id", ignore = true)
    Note toEntity(NoteReqDTO noteReqDTO);
}
