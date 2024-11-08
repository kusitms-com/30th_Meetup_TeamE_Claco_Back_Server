package com.curateme.claco.recommendation.service;

import com.curateme.claco.clacobook.domain.entity.ClacoBook;
import com.curateme.claco.clacobook.repository.ClacoBookRepository;
import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.repository.ConcertLikeRepository;
import com.curateme.claco.concert.repository.ConcertRepository;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponse;
import com.curateme.claco.review.domain.dto.response.TicketReviewSummaryResponse;
import com.curateme.claco.review.domain.entity.TicketReview;
import com.curateme.claco.review.repository.TicketReviewRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private final ClacoBookRepository clacoBookRepository;
    private final TicketReviewRepository ticketReviewRepository;

    // 유저 취향 기반 공연 추천
    @Override
    public List<RecommendationConcertsResponse> getConcertRecommendations(Long userId) {
        String FLASK_API_URL = "http://localhost:8081/recommendations/users/";

        String jsonResponse = getConcertsFromFlask(userId, FLASK_API_URL);
        System.out.println("jsonResponse = " + jsonResponse);

        List<Long> concertIds = parseConcertIdsFromJson(jsonResponse);

        return getConcertDetails(concertIds);
    }

    //최근 좋아요한 공연 기반 추천
    @Override
    public List<RecommendationConcertsResponse> getLikedConcertRecommendations(Long userId) {

        Long concertId = concertLikeRepository.findMostRecentLikedConcert(userId);

        String FLASK_API_URL = "http://localhost:8081/recommendations/items/";

        String jsonResponse = getConcertsFromFlask(concertId, FLASK_API_URL);
        System.out.println("jsonResponse = " + jsonResponse);

        List<Long> concertIds = parseConcertIdsFromJson(jsonResponse);

        return getConcertDetails(concertIds);
    }

    // 유저 취향 기반 클라코북 추천
    @Override
    public List<RecommendationConcertsResponse> getClacoBooksRecommendations(Long userId) {

        String FLASK_API_URL = "http://localhost:8081/recommendations/clacobooks/";

        String jsonResponse = getConcertsFromFlask(userId, FLASK_API_URL);
        System.out.println("jsonResponse = " + jsonResponse);

        List<Long> RecUserIds = parseConcertIdsFromJson(jsonResponse);
        Long RecUserId = RecUserIds.get(0);

        Optional<ClacoBook> clacoBook = clacoBookRepository.findByMemberId(RecUserId);
        Optional<TicketReview> ticketReview = clacoBookRepository.findRandomTicketReviewByClacoBookId(clacoBook.get()
            .getId());
        Optional<TicketReviewSummaryResponse> ticketReviewSummaryResponse = ticketReviewRepository.findSummaryById(ticketReview.get()
            .getId());



        return null;
    }

    // JSON 응답을 파싱하여 concertIds 리스트 생성
    private List<Long> parseConcertIdsFromJson(String jsonResponse) {
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
        return concertIds;
    }

    // concertIds를 기반으로 콘서트 정보를 조회하여 recommendations 리스트 생성
    private List<RecommendationConcertsResponse> getConcertDetails(List<Long> concertIds) {
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

    public String getConcertsFromFlask(Long Id, String FLASK_API_URL) {
        String urlWithUserId = FLASK_API_URL + Id;

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
