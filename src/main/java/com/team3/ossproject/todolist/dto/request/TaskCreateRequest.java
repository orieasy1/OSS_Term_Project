package com.team3.ossproject.todolist.dto.request;


import com.team3.ossproject.todolist.domain.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request DTO for creating a new task")
public class TaskCreateRequest {

    @Schema(description = "Task title", example = "Finish project")
    private String title;

    @Schema(description = "Task description", example = "Complete the BPM assignment")
    private String description;

    @Schema(description = "Day value", example = "25")
    private Integer day;

    @Schema(description = "Week value", example = "52")
    private Integer week;

    @Schema(description = "Month value", example = "12")
    private Integer month;

    @Schema(description = "Year value", example = "2024")
    private Integer year;

    @Schema(description = "Task priority", example = "high")
    private String priority;


    public Priority getPriorityAsEnum() {
        return Priority.fromName(priority.toUpperCase());
    }
}

