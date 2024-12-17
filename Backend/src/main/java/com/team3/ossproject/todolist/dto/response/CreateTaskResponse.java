package com.team3.ossproject.todolist.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response DTO for creating a new task")
public class CreateTaskResponse {

    @Schema(description = "Task title", example = "Finish project")
    private String title;

    @Schema(description = "Task description", example = "Complete the BPM assignment")
    private String description;

    @Schema(description = "Task type", example = "month")
    private String type;

}
