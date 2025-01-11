package com.note.demo.model.DTO;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Builder
@Getter
@Setter
public class NoteReqDTO {
    @NotNull private String title;
    @Nullable private String content;
    @NotNull private Boolean archived;
    private List<String> categories;
}
