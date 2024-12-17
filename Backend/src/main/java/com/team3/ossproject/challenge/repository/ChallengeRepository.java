package com.team3.ossproject.challenge.repository;

import com.team3.ossproject.challenge.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
