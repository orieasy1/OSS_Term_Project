package com.team3.ossproject.todolist.dto.response;

import com.team3.ossproject.todolist.domain.Status;
import com.team3.ossproject.todolist.domain.Task;
import com.team3.ossproject.todolist.domain.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Schema(description = "Response DTO for searching todolist")
public class TaskResponse {
    @Schema(description = "Type", example = "DAY")
    private String type;

    @Schema(description = "Todoat(Day16/Week14/Month12)", example = "16")
    private int todoat;

    @Schema(description = "Todolist")
    private List<Todolist> todolist;

    @Data
    public static class Todolist {

        @Schema(description = "Task Id", example = "1")
        private Long taskId;

        @Schema(description = "Task title", example = "Finish project")
        private String title;

        @Schema(description = "Task description", example = "Complete the BPM assignment")
        private String description;

        @Schema(description = "Task Status", example = "PENDING")
        private String status;

        @Schema(description = "Task priority", example = "high")
        private String priority;

        @Builder
        public Todolist (Long taskId, String title, String description, String status, String priority) {
            this.taskId = taskId;
            this.title = title;
            this.description = description;
            this.status = status;
            this.priority= priority;

        }
    }

    @Builder
    public TaskResponse (String type, int todoat, List<Todolist> todolist) {
        this.type = type;
        this.todoat = todoat;
        this.todolist = todolist;
    }
}
