package com.team3.ossproject.todolist.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TaskUpdateResponse {

    @Schema(description = "ID of the task", example = "12345")
    private Long taskId;

    @Schema(description = "Title of the task", example = "Updated task title")
    private String title;

    @Schema(description = "Description of the task", example = "Updated description of the task")
    private String description;

    @Schema(description = "Priority of the task", example = "high")
    private String priority;

    @Schema(description = "Timestamp when the task was last updated", example = "2024-12-07T12:34:56")
    private LocalDateTime updatedAt;
}

