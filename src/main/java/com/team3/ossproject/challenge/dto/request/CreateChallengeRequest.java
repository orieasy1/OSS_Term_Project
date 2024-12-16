package com.team3.ossproject.challenge.dto.request;

import com.team3.ossproject.challenge.domain.Duration;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Schema(description = "Request DTO for creating a new challenge")
public class CreateChallengeRequest {

    @NotNull(message = "Title is required")
    @Schema(description = "Challenge title", example = "Study English Voca")
    private String title;

    @NotNull(message = "Title is required")
    @Schema(description = "Challenge Description", example = "Study vocabulary for IELTS")
    private String description;

    @NotNull(message = "Title is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Start Date", example = "2024-12-16")
    private LocalDate startDate;

    @NotNull(message = "Title is required")
    @Schema(description = "Duration of Challenge", example = "DAY_50")
    private Duration duration;

}

