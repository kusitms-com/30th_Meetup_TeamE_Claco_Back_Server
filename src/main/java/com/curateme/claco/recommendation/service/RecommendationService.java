package com.curateme.claco.recommendation.service;

import com.curateme.claco.recommendation.domain.dto.RecommendationConcertResponseV2;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponseV1;
import java.util.List;

public interface RecommendationService {
    List<RecommendationConcertsResponseV1> getConcertRecommendations(Long userId);
    List<RecommendationConcertsResponseV1> getLikedConcertRecommendations(Long userId);
    RecommendationConcertResponseV2 getClacoBooksRecommendations(Long userId);


}
