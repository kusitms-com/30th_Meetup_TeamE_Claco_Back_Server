package com.curateme.claco.recommendation.service;

import com.curateme.claco.recommendation.domain.dto.RecommendationConcertResponseV2;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertResponseV3;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponseV1;
import java.util.List;

public interface RecommendationService {
    List<RecommendationConcertsResponseV1> getConcertRecommendations();
    RecommendationConcertResponseV3 getLikedConcertRecommendations();
    RecommendationConcertResponseV2 getClacoBooksRecommendations();


}
