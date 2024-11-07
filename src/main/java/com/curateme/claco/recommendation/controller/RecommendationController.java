package com.curateme.claco.recommendation.controller;

import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponse;
import com.curateme.claco.recommendation.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/userbased")
    @Operation(summary = "나의 취향 기반 맞춤 추천", description = "기능명세서 화면번호 2.0.0(C)")
    public ApiResponse<List<RecommendationConcertsResponse>> getConcertRecommendations(
        @RequestParam Long userId
    ){
        return ApiResponse.ok(recommendationService.getConcertRecommendations(userId));
    }

    @GetMapping("/itembased")
    @Operation(summary = "최근 좋아요 한 공연 기반 맞춤 추천", description = "기능명세서 화면번호 2.1.0(C)")
    public ApiResponse<List<RecommendationConcertsResponse>> getLikedConcertRecommendations(
        @RequestParam Long userId
    ){
        return ApiResponse.ok(recommendationService.getLikedConcertRecommendations(userId));
    }
}
