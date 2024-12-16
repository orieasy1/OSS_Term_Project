package com.team3.ossproject.challenge.service;

import com.team3.ossproject.challenge.domain.Challenge;
import com.team3.ossproject.challenge.domain.Duration;
import com.team3.ossproject.challenge.domain.Status;
import com.team3.ossproject.challenge.dto.request.CreateChallengeRequest;
import com.team3.ossproject.challenge.exception.ChallengeErrorCode;
import com.team3.ossproject.challenge.exception.ChallengeException;
import com.team3.ossproject.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public String createChallenge(Long userId, CreateChallengeRequest request){

        // Duration 변환 (String -> Enum)
        Duration duration = request.getDuration();

        LocalDate finishedAt = LocalDate.now().plusDays(duration == Duration.DAY_50 ? 50 : 100);

        long remainingDays = calculateRemainingDays(finishedAt);

        // Challenge 엔티티 빌드
        Challenge challenge = Challenge.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(LocalDate.now())
                .duration(duration)
                .status(Status.INPROGRESS)
                .count(0)
                .progressRate(0)
                .remainingDays(remainingDays)
                .finishedAt(finishedAt)
                .build();

        Challenge savedChallenge = challengeRepository.save(challenge);

        return savedChallenge.getTitle();
    }


    private long calculateRemainingDays(LocalDate finishedAt) {
        LocalDate today = LocalDate.now();
        if (finishedAt.isBefore(today)) {
            throw new ChallengeException(ChallengeErrorCode.PAST_DATE_NOT_ALLOWED);
        }
        return ChronoUnit.DAYS.between(today, finishedAt);
    }

}
