package com.team3.ossproject.challenge.controller;

import com.team3.ossproject.challenge.dto.request.CreateChallengeRequest;
import com.team3.ossproject.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping("/create")
    public String getChallengeCreatePage() {

        return "index";
    }



    /**
     * 챌린지 등록 처리 (POST)
     */
    @PostMapping("/create")
    public String createChallenge(
            CreateChallengeRequest request,
            Model model) {

        // 챌린지 생성
        String title = challengeService.createChallenge(request);
        model.addAttribute("message", title + "Challenge created successfully!");
        return "redirect:/api/v1/challenges";
    }


}
