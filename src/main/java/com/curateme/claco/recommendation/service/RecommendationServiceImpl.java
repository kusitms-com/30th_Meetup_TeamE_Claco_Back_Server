package com.curateme.claco.recommendation.service;

import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.repository.ConcertLikeRepository;
import com.curateme.claco.concert.repository.ConcertRepository;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService{

    private final ConcertRepository concertRepository;
    private final ConcertLikeRepository concertLikeRepository;

    @Override
    public List<RecommendationConcertsResponse> getConcertRecommendations(Long userId) {

        String jsonResponse = getConcertsFromFlask(userId);
        System.out.println("jsonResponse = " + jsonResponse);
        // 추천시스템에서 받아온 콘서트 id 값
        List<Long> concertIds = new ArrayList<>();
        if (jsonResponse != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(jsonResponse);
                JsonNode recommendationsArray = root.get("recommendations");

                for (JsonNode recommendation : recommendationsArray) {
                    Long concertId = Long.parseLong(recommendation.get(0).asText());
                    concertIds.add(concertId);
                }
            } catch (Exception e) {
                System.err.println("Error parsing recommendations: " + e.getMessage());
            }
        }

        // 콘서트 정보 반환 값들
        List<RecommendationConcertsResponse> recommendations = new ArrayList<>();

        for (Long concertId : concertIds) {
            Concert concert = concertRepository.findConcertById(concertId);
            Long id = concert.getId();

            recommendations.add(new RecommendationConcertsResponse(
                    id,
                    concert.getPrfnm(),
                    concert.getPoster(),
                    concert.getGenrenm(),
                    concertLikeRepository.existsByConcertId(id)
                ));
        }

        return recommendations;
    }

    private static final String FLASK_API_URL = "http://localhost:8081/recommendations/users/";
    public String getConcertsFromFlask(Long userId) {
        String urlWithUserId = FLASK_API_URL + userId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(urlWithUserId, HttpMethod.GET, requestEntity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                System.err.println("추천시스템 오류 발생. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("추천시스템 실패.: " + e.getMessage());
        }
        return null;
    }

}
