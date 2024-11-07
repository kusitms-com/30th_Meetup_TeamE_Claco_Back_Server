package com.curateme.claco.recommendation.controller;

import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponse;
import com.curateme.claco.recommendation.service.RecommendationService;
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

    @GetMapping
    public ApiResponse<List<RecommendationConcertsResponse>> getConcertRecommendations(
        @RequestParam Long userId
    ){
        return ApiResponse.ok(recommendationService.getConcertRecommendations(userId));
    }
}
