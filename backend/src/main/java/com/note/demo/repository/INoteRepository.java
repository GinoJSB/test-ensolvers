package com.note.demo.repository;

import com.note.demo.model.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE :category MEMBER OF n.categories")
    List<Note> findByCategory(@Param("category") String category);
}
