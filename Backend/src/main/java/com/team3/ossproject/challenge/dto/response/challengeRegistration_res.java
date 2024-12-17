package com.team3.ossproject.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team3.ossproject.challenge.domain.Challenge;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "Challenge creation/response DTO")
public class challengeRegistration_res {

    @Schema(description = "Challenge ID", example = "1")
    private Long challengeId;

    @Schema(description = "Challenge title", example = "100-Day Coding Challenge")
    private String title;

    @Schema(description = "Challenge description", example = "Solve one problem every day")
    private String description;

    @Schema(description = "Challenge start date", example = "2024-12-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Schema(description = "Challenge duration", example = "50")
    private Integer duration;

    @Schema(description = "Challenge status", example = "pending")
    private String status;

    @Schema(description = "Challenge creation date", example = "2024-11-30")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Schema(description = "Challenge finish date", example = "2024-01-19", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishedAt;

    @Schema(description = "Remaining days", example = "50")
    private Integer remainingDays;

    @Builder
    public ChallengeResponseDTO(Long challengeId, String title, String description, LocalDate startDate, Integer duration, String status, LocalDate createdAt, LocalDate finishedAt, Integer remainingDays) {
        this.challengeId = challengeId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.duration = duration;
        this.status = status;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.remainingDays = remainingDays;
    }

    public static challengeRegistration_res from(Challenge challenge) {
        return challengeRegistration_res.builder()
                .challengeId(challenge.getId())
                .title(challenge.getTitle())
                .description(challenge.getDescription())
                .startDate(challenge.getStartDate())
                .duration(challenge.getDuration())
                .status(calculateStatus(challenge.getStartDate()))
                .createdAt(challenge.getCreatedAt())
                .finishedAt(calculateFinishedAt(challenge.getStartDate(), challenge.getDuration()))
                .remainingDays(calculateRemainingDays(challenge.getStartDate(), challenge.getDuration()))
                .build();
    }

    private static String calculateStatus(LocalDate startDate) {
        LocalDate today = LocalDate.now();
        if (startDate.isEqual(today)) {
            return "inprogress";
        } else if (startDate.isAfter(today)) {
            return "pending";
        } else {
            return "completed";
        }
    }

    private static LocalDate calculateFinishedAt(LocalDate startDate, Integer duration) {
        return startDate.plusDays(duration);
    }

    private static Integer calculateRemainingDays(LocalDate startDate, Integer duration) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = calculateFinishedAt(startDate, duration);
        return (int) today.until(endDate).getDays();
    }
}

