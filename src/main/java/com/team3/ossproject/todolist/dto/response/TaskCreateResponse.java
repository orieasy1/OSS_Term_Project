package com.team3.ossproject.todolist.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Response DTO for creating a new task")
public class TaskCreateResponse {

    @Schema(description = "Task title", example = "Finish project")
    private String title;

    @Schema(description = "Task type", example = "month")
    private String type;

    @Schema(description = "Todo at", example = "2024-12-16")
    private String todoat;

    @Builder
    public TaskCreateResponse(String title, String type, String todoat) {
        this.title = title;
        this.type = type;
        this.todoat = todoat;
    }
}
