package com.curateme.claco.recommendation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.curateme.claco.authentication.util.SecurityContextUtil;
import com.curateme.claco.clacobook.repository.ClacoBookRepository;
import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.repository.ConcertCategoryRepository;
import com.curateme.claco.concert.repository.ConcertLikeRepository;
import com.curateme.claco.concert.repository.ConcertRepository;
import com.curateme.claco.member.repository.MemberRepository;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertResponseV3;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponseV1;
import com.curateme.claco.recommendation.service.RecommendationServiceImpl;
import com.curateme.claco.review.repository.TicketReviewRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceImplTest {

    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @Mock
    private ConcertRepository concertRepository;

    @Mock
    private ConcertLikeRepository concertLikeRepository;

    @Mock
    private ConcertCategoryRepository concertCategoryRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setup() {
        // Mock Concert entities
        when(concertRepository.findConcertById(1L)).thenReturn(
            Concert.builder()
                .id(1L)
                .prfnm("클래식 콘서트")
                .prfpdfrom(LocalDate.of(2024, 1, 1))
                .prfpdto(LocalDate.of(2024, 12, 31))
                .genrenm("Classical")
                .build()
        );

        when(concertRepository.findConcertById(2L)).thenReturn(
            Concert.builder()
                .id(2L)
                .prfnm("클래식 콘서트 2")
                .prfpdfrom(LocalDate.of(2024, 1, 1))
                .prfpdto(LocalDate.of(2024, 12, 31))
                .genrenm("Classical")
                .build()
        );

    }


    @Test
    void testGetConcertRecommendations_Success() {

        // Given
        String mockJsonResponse = """
            {
                "recommendations": [
                    [1, 0.9],
                    [2, 0.7]
                ]
            }
        """;

        // RestTemplate Mock 설정
        try (MockedConstruction<RestTemplate> mocked = mockConstruction(RestTemplate.class, (mock, context) -> {
            when(mock.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok(mockJsonResponse));
        })) {
            // When
            List<Long> concertIds = recommendationService.parseConcertIdsFromJson(mockJsonResponse);
            List<RecommendationConcertsResponseV1> recommendations = recommendationService.getConcertDetails(concertIds);

            // Then
            assertThat(recommendations).isNotNull();
            assertThat(recommendations).hasSize(2);
            assertThat(recommendations.get(0).getId()).isEqualTo(1L);
            assertThat(recommendations.get(1).getId()).isEqualTo(2L);
        }
    }

    @Test
    void testGetLikedConcertRecommendations_WithLikedConcert() {
        // Given: The user has liked a concert
        when(concertLikeRepository.findMostRecentLikedConcert(eq(1L), any(Pageable.class)))
            .thenReturn(new PageImpl<>(Collections.singletonList(1L)));

        when(concertCategoryRepository.findCategoryNamesByConcertId(1L))
            .thenReturn(Arrays.asList("Category1", "Category2", "Category3"));

        when(concertRepository.findConcertById(1L)).thenReturn(
            Concert.builder()
                .id(1L)
                .prfnm("Classical Concert")
                .prfpdfrom(LocalDate.of(2024, 1, 1))
                .prfpdto(LocalDate.of(2024, 12, 31))
                .genrenm("Classical")
                .build()
        );

        String mockJsonResponse = """
            {
                "recommendations": [[1, 0.9], [2, 0.8]]
            }
        """;
        // When
        Pageable pageable = PageRequest.of(0, 1);
        Long concertId = concertLikeRepository.findMostRecentLikedConcert(1L, pageable)
            .getContent().stream().findFirst().orElse(null);
        List<Long> concertIds = recommendationService.parseConcertIdsFromJson(mockJsonResponse);
        List<RecommendationConcertsResponseV1> recommendedConcerts = recommendationService.getConcertDetails(concertIds);
        List<String> keywords = concertCategoryRepository.findCategoryNamesByConcertId(concertId);
        // When
        RecommendationConcertResponseV3 response =
            RecommendationConcertResponseV3.builder()
            .likedHistory(true)
            .keywords(keywords)
            .recommendationConcertsResponseV1s(recommendedConcerts)
            .build();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getLikedHistory()).isTrue();
        assertThat(response.getKeywords()).containsExactly("Category1", "Category2", "Category3");
        assertThat(response.getRecommendationConcertsResponseV1s()).hasSize(2);
        assertThat(response.getRecommendationConcertsResponseV1s().get(0).getId()).isEqualTo(1L);
        assertThat(response.getRecommendationConcertsResponseV1s().get(1).getId()).isEqualTo(2L);
    }

    @Test
    void testGetLikedConcertRecommendations_NoLikedConcert() {
        // Given
        when(concertLikeRepository.findTopConcertIdsByLikeCount(any(Pageable.class)))
            .thenReturn(Arrays.asList(1L, 2L));

        when(concertCategoryRepository.findCategoryNamesByConcertId(1L))
            .thenReturn(Arrays.asList("TopCategory1", "TopCategory2"));

        when(concertRepository.findConcertById(1L)).thenReturn(
            Concert.builder()
                .id(1L)
                .prfnm("Classical Concert")
                .prfpdfrom(LocalDate.of(2024, 1, 1))
                .prfpdto(LocalDate.of(2024, 12, 31))
                .genrenm("Classical")
                .build()
        );

        when(concertRepository.findConcertById(2L)).thenReturn(
            Concert.builder()
                .id(2L)
                .prfnm("Pop Concert")
                .prfpdfrom(LocalDate.of(2024, 2, 1))
                .prfpdto(LocalDate.of(2024, 12, 31))
                .genrenm("Pop")
                .build()
        );

        // When
        Pageable pageable = PageRequest.of(0, 3);
        List<Long> topConcertIds = concertLikeRepository.findTopConcertIdsByLikeCount(pageable);
        List<RecommendationConcertsResponseV1> recommendedConcerts = recommendationService.getConcertDetails(topConcertIds);
        List<String> keywords = concertCategoryRepository.findCategoryNamesByConcertId(1L);

        RecommendationConcertResponseV3 response =
            RecommendationConcertResponseV3.builder()
                .likedHistory(false)
                .keywords(keywords)
                .recommendationConcertsResponseV1s(recommendedConcerts)
                .build();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getLikedHistory()).isFalse();
        assertThat(response.getKeywords()).containsExactly("TopCategory1", "TopCategory2");
        assertThat(response.getRecommendationConcertsResponseV1s()).hasSize(2);
        assertThat(response.getRecommendationConcertsResponseV1s().get(0).getId()).isEqualTo(1L);
        assertThat(response.getRecommendationConcertsResponseV1s().get(1).getId()).isEqualTo(2L);
    }

    @Test
    void testGetSearchedConcertRecommendations_Success() {
        // Given
        String mockJsonResponse = """
        {
            "recommendations": [[1, 0.9], [2, 0.8], [3, 0.7]]
        }
    """;

        when(concertRepository.findConcertById(1L)).thenReturn(
            Concert.builder()
                .id(1L)
                .prfnm("Classical Concert")
                .prfpdfrom(LocalDate.of(2024, 1, 1))
                .prfpdto(LocalDate.of(2024, 12, 31))
                .genrenm("Classical")
                .build()
        );

        when(concertRepository.findConcertById(2L)).thenReturn(
            Concert.builder()
                .id(2L)
                .prfnm("Pop Concert")
                .prfpdfrom(LocalDate.of(2024, 2, 1))
                .prfpdto(LocalDate.of(2024, 12, 31))
                .genrenm("Pop")
                .build()
        );

        when(concertRepository.findConcertById(3L)).thenReturn(
            Concert.builder()
                .id(3L)
                .prfnm("Jazz Concert")
                .prfpdfrom(LocalDate.of(2024, 3, 1))
                .prfpdto(LocalDate.of(2024, 12, 31))
                .genrenm("Jazz")
                .build()
        );

        // When
        List<Long> concertIds = recommendationService.parseConcertIdsFromJson(mockJsonResponse);
        List<RecommendationConcertsResponseV1> recommendations = recommendationService.getConcertDetails(concertIds);

        // Then
        assertThat(recommendations).isNotNull();
        assertThat(recommendations).hasSize(3);
        assertThat(recommendations.get(0).getId()).isEqualTo(1L);
        assertThat(recommendations.get(1).getId()).isEqualTo(2L);
        assertThat(recommendations.get(2).getId()).isEqualTo(3L);
    }

}

