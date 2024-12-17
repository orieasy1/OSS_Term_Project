package com.team3.ossproject.challenge.service;

import com.team3.ossproject.challenge.domain.Challenge;
import com.team3.ossproject.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


package com.team3.ossproject.challenge.service;

import com.team3.ossproject.challenge.dto.request.challengeRegistration;
import com.team3.ossproject.challenge.dto.response.challengeRegistration_res;
import com.team3.ossproject.challenge.entity.Challenge;
import com.team3.ossproject.challenge.exception.ChallengeErrorCode;
import com.team3.ossproject.challenge.exception.ChallengeException;
import com.team3.ossproject.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Transactional
@Service
public class challengeRegis_service {

    private final ChallengeRepository challengeRepository;

    public challengeRegistration_res createChallenge(challengeRegistration request) {
        validateRequest(request);

        Challenge challenge = Challenge.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .duration(request.getDuration())
                .status(determineStatus(request.getStartDate()))
                .createdAt(LocalDate.now())
                .build();

        challengeRepository.save(challenge);

        return challengeRegistration_res.from(challenge);
    }

    @Transactional(readOnly = true)
    public challengeRegistration_res getChallengeById(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new ChallengeException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));

        return challengeRegistration_res.from(challenge);
    }

    private void validateRequest(challengeRegistration request) {
        if (request.getTitle() == null || request.getTitle().isEmpty()) {
            throw new ChallengeException(ChallengeErrorCode.INVALID_REQUEST, "Title is required");
        }

        if (request.getStartDate() == null) {
            throw new ChallengeException(ChallengeErrorCode.INVALID_REQUEST, "Start date is required");
        }

        if (request.getDuration() != 50 && request.getDuration() != 100) {
            throw new ChallengeException(ChallengeErrorCode.INVALID_REQUEST, "Duration must be 50 or 100");
        }
    }

    private String determineStatus(LocalDate startDate) {
        LocalDate today = LocalDate.now();
        if (startDate.isEqual(today)) {
            return "inprogress";
        } else if (startDate.isAfter(today)) {
            return "pending";
        } else {
            return "completed";
        }
    }
}

