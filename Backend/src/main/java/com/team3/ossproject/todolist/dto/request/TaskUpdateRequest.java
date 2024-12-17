package com.team3.ossproject.todolist.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateRequest {

    @Schema(description = "Title of the task", example = "Updated task title")
    private String title;

    @Schema(description = "Description of the task", example = "Updated description of the task")
    private String description;

    @Schema(description = "Priority of the task", example = "high")
    private String priority;
}
