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
//
//    private final ChallengeService challengeService;
//
//    @GetMapping("/create")
//    public String getChallengeCreatePage(
//            @RequestParam(value = "token", required = false) String token,
//            Model model) {
//        if (token == null || token.isEmpty()) {
//            model.addAttribute("error", "토큰이 필요합니다.");
//            return "error"; // 에러 페이지
//        }
//        model.addAttribute("challenge", new CreateChallengeRequest());
//        model.addAttribute("token", token); // 토큰 추가
//        return "challenge_create"; // challenge_create.html 반환
//    }
//
//
//
//    /**
//     * 챌린지 등록 처리 (POST)
//     */
//    @PostMapping("/create")
//    public String createChallenge(
//            CreateChallengeRequest request,
//            @RequestParam(value = "token") String token,
//            Model model) {
//        System.out.println("Duration received: " + request.getDuration()); // 로그로 확인
//        System.out.println("Title received: " + request.getTitle());
//        System.out.println("description received: " + request.getTitle());
//
//
//        // 챌린지 생성
//        String title = challengeService.createChallenge(request);
//        model.addAttribute("message", title + "Challenge created successfully!");
//        return "redirect:/api/v1/challenges";
//    }
//

}
