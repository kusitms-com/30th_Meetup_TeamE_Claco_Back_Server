package com.curateme.claco.recommendation.service;

import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponse;
import java.util.List;

public interface RecommendationService {
    List<RecommendationConcertsResponse> getConcertRecommendations(Long userId);
    List<RecommendationConcertsResponse> getLikedConcertRecommendations(Long userId);
    List<RecommendationConcertsResponse> getClacoBooksRecommendations(Long userId);


}
