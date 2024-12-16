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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/challenge")
@Tag(name = "Challenge Controller", description = "[Challenge] Challenge API")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    @Operation(summary = "Add Challenge")
    public ResponseEntity<ApiResponse<StringResponseDto>> addChallenge(
            @RequestBody CreateChallengeRequest request){

        String response = "Challenge " + challengeService.createChallenge(request) + " is successfully created!";
        return ResponseEntity.ok(ApiResponse.success(new StringResponseDto(response)));
    }


}
