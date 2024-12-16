package com.team3.ossproject.challenge.controller;

import com.team3.ossproject.challenge.dto.request.CreateChallengeRequest;
import com.team3.ossproject.challenge.dto.response.ChallengeListResponse;
import com.team3.ossproject.challenge.service.ChallengeService;
import com.team3.ossproject.global.dto.ApiResponse;
import com.team3.ossproject.global.dto.StringResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


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


}
