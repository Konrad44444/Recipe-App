package com.course.recipe.repipe_project.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotesCommand {
    private Long id;
    private String note;
}
