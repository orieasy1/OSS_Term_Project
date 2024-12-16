package com.team3.ossproject.challenge.controller;

import com.team3.ossproject.challenge.dto.request.challengeRegistration;
import com.team3.ossproject.challenge.dto.response.challengeRegistration_res;
import com.team3.ossproject.challenge.exception.ChallengeErrorCode;
import com.team3.ossproject.challenge.service.challengeRegis_service;
import com.team3.ossproject.global.dto.ErrorReason;
import com.team3.ossproject.global.dto.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/challenge")
@Tag(name = "Challenge Controller", description = "[Challenge] Challenge API")

public class ChallengeController {
    private final challengeRegis_service challengeService;

    @PostMapping
    @Operation(summary = "Create a new challenge")
    public ResponseEntity<SuccessResponse<challengeRegistration_res>> createChallenge(
            @RequestBody challengeRegistration request) {
        challengeRegistration_res response = challengeService.createChallenge(request);
        return ResponseEntity.ok(SuccessResponse.success(response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get challenge details by ID")
    public ResponseEntity<SuccessResponse<challengeRegistration_res>> getChallengeById(
            @PathVariable("id") Long id) {
        challengeRegistration_res response = challengeService.getChallengeById(id);
        return ResponseEntity.ok(SuccessResponse.success(response));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ChallengeErrorCode> handleException(Exception ex) {
        ChallengeErrorCode errorResponse = new ErrorReason(400, "BAD_REQUEST", ex.getMessage(), "/api/v1/challenges");
        return ResponseEntity.status(400).body(errorResponse);
    }
    }

    @GetMapping
    @Operation(summary = "챌린지 조회")
    public String findChallenge(){
        return null;
    }
}
