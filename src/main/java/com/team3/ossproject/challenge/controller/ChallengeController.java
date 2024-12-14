package com.team3.ossproject.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/challenge")
@Tag(name = "Challenge Controller", description = "[Challenge] Challenge API")

public class ChallengeController {
    @PostMapping
    @Operation(summary = "챌린지 등록")
    public String addChallenge(){
        return null;
    }

    @GetMapping
    @Operation(summary = "챌린지 조회")
    public String findChallenge(){
        return null;
    }
}
