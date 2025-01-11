package com.note.demo.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private boolean archived = false;

    @ElementCollection
    private List<String> categories = new ArrayList<>(); // Campo para categorías

    public Note() {}

    public Note(Long id, String title, String content, boolean archived, List<String> categories) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.archived = archived;
        this.categories = categories;
    }
}
