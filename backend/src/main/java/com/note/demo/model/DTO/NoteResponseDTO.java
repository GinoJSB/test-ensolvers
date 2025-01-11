package com.note.demo.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class NoteResponseDTO {
    private Long id;
    private String title;
    private String content;
    private boolean archived;
    private List<String> categories;
}
