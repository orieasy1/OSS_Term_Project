package com.team3.ossproject.challenge.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "챌린지 목록 조회 응답 DTO")
public class ChallengeListResponse {

    @Schema(description = "진행 중인 챌린지 목록")
    private List<InProgressChallenge> inprogress;

    @Builder
    public ChallengeListResponse(List<InProgressChallenge> inprogress) {
        this.inprogress = inprogress;
    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "진행 중인 챌린지")
    public static class InProgressChallenge {

        @Schema(description = "챌린지 제목", example = "Fitness Challenge")
        private String title;

        @Schema(description = "남은 일수", example = "15")
        private int remainingDays;

        @Schema(description = "진행률(%)", example = "35.5")
        private double progressRate;

        @Builder
        public InProgressChallenge(String title, int remainingDays, double progressRate) {
            this.title = title;
            this.remainingDays = remainingDays;
            this.progressRate = progressRate;
        }
    }
}
