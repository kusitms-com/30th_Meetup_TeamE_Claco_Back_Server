package com.curateme.claco.recommendation.controller;

import com.curateme.claco.global.annotation.TokenRefreshedCheck;
import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertResponseV2;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertResponseV3;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponseV1;
import com.curateme.claco.recommendation.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@TokenRefreshedCheck
@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/userbased")
    @Operation(summary = "나의 취향 기반 맞춤 추천", description = "기능명세서 화면번호 2.0.0(C)")
    public ApiResponse<List<RecommendationConcertsResponseV1>> getConcertRecommendations(
    ){
        return ApiResponse.ok(recommendationService.getConcertRecommendations());
    }

    @GetMapping("/itembased")
    @Operation(summary = "최근 좋아요 한 공연 기반 맞춤 추천", description = "기능명세서 화면번호 2.1.0(C)")
    public ApiResponse<RecommendationConcertResponseV3> getLikedConcertRecommendations(
    ){
        return ApiResponse.ok(recommendationService.getLikedConcertRecommendations());
    }

    @GetMapping("/clacobooks")
    @Operation(summary = "유저 취향 기반 클라코북 맞춤 추천", description = "기능명세서 화면번호 2.2.0")
    public ApiResponse<RecommendationConcertResponseV2> getClacoBooksRecommendations(
    ){
        return ApiResponse.ok(recommendationService.getClacoBooksRecommendations());
    }
}
