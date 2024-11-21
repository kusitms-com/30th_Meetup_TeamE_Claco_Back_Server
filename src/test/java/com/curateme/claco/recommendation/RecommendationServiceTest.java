package com.curateme.claco.recommendation;

import static org.assertj.core.api.Assertions.assertThat;

import com.curateme.claco.recommendation.domain.dto.RecommendationConcertResponseV2;
import com.curateme.claco.recommendation.domain.dto.RecommendationConcertsResponseV1;
import com.curateme.claco.review.domain.dto.response.TicketInfoResponse;
import com.curateme.claco.review.domain.dto.response.TicketReviewSummaryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceImplTest {

    @Test
    void testGetMockRecommendations() {

        // Given
        List<RecommendationConcertsResponseV1> mockRecommendations = List.of(
            new RecommendationConcertsResponseV1(
                101L,
                "Mock Concert 1",
                "https://mock.poster/1",
                "Classical",
                "Mock Facility 1",
                LocalDate.of(2024, 1, 1).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN)),
                LocalDate.of(2024, 12, 31).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN))
            ),
            new RecommendationConcertsResponseV1(
                102L,
                "Mock Concert 2",
                "https://mock.poster/2",
                "Jazz",
                "Mock Facility 2",
                LocalDate.of(2024, 2, 1).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN)),
                LocalDate.of(2024, 11, 30).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN))
            )
        );

        // When
        List<RecommendationConcertsResponseV1> recommendations = mockRecommendations;

        // Then
        assertThat(recommendations).isNotNull();
        assertThat(recommendations).hasSize(2);
        assertThat(recommendations.get(0).getPrfnm()).isEqualTo("Mock Concert 1");
        assertThat(recommendations.get(1).getPrfnm()).isEqualTo("Mock Concert 2");

    }

    @Test
    void testGetMockLikedRecommendations() {
        // Given
        List<RecommendationConcertsResponseV1> mockLikedRecommendations = List.of(
            new RecommendationConcertsResponseV1(
                201L,
                "Liked Concert 1",
                "https://mock.poster/201",
                "Pop",
                "Mock Facility A",
                LocalDate.of(2024, 3, 1).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN)),
                LocalDate.of(2024, 9, 30).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN))
            ),
            new RecommendationConcertsResponseV1(
                202L,
                "Liked Concert 2",
                "https://mock.poster/202",
                "Rock",
                "Mock Facility B",
                LocalDate.of(2024, 5, 1).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN)),
                LocalDate.of(2024, 10, 31).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN))
            )
        );

        // When
        List<RecommendationConcertsResponseV1> recommendations = mockLikedRecommendations;

        // Then
        assertThat(recommendations).isNotNull();
        assertThat(recommendations).hasSize(2);
        assertThat(recommendations.get(0).getPrfnm()).isEqualTo("Liked Concert 1");
        assertThat(recommendations.get(1).getPrfnm()).isEqualTo("Liked Concert 2");
    }

    @Test
    void testGetMockClacoBooksRecommendations() {
        // Given
        List<RecommendationConcertResponseV2> mockClacoBooksRecommendations = List.of(
            RecommendationConcertResponseV2.from(
                new TicketInfoResponse(301L, "https://mock.ticket/301"),
                new TicketReviewSummaryResponse(
                    "User1", "Concert 301", 301L, LocalDateTime.of(2024, 5, 10, 15, 30), "Amazing concert!"
                )
            ),
            RecommendationConcertResponseV2.from(
                new TicketInfoResponse(302L, "https://mock.ticket/302"),
                new TicketReviewSummaryResponse(
                    "User2", "Concert 302", 302L, LocalDateTime.of(2024, 6, 20, 18, 0), "Great performance!"
                )
            )
        );

        // When
        List<RecommendationConcertResponseV2> recommendations = mockClacoBooksRecommendations;

        // Then
        assertThat(recommendations).isNotNull();
        assertThat(recommendations).hasSize(2);
        assertThat(recommendations.get(0).getTicketInfoResponse().getTicketImage()).isEqualTo("https://mock.ticket/301");
        assertThat(recommendations.get(1).getTicketReviewSummary().getContent()).isEqualTo("Great performance!");
    }

    @Test
    void testGetMockSearchedConcertRecommendations() {
        // Given
        List<RecommendationConcertsResponseV1> mockSearchedConcertRecommendations = List.of(
            new RecommendationConcertsResponseV1(
                401L,
                "Searched Concert 1",
                "https://mock.poster/401",
                "Jazz",
                "Mock Facility C",
                LocalDate.of(2024, 7, 1).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN)),
                LocalDate.of(2024, 12, 31).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN))
            ),
            new RecommendationConcertsResponseV1(
                402L,
                "Searched Concert 2",
                "https://mock.poster/402",
                "Classical",
                "Mock Facility D",
                LocalDate.of(2024, 8, 1).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN)),
                LocalDate.of(2024, 11, 30).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", Locale.KOREAN))
            )
        );

        // When
        List<RecommendationConcertsResponseV1> recommendations = mockSearchedConcertRecommendations;

        // Then
        assertThat(recommendations).isNotNull();
        assertThat(recommendations).hasSize(2);
        assertThat(recommendations.get(0).getPrfnm()).isEqualTo("Searched Concert 1");
        assertThat(recommendations.get(1).getPrfnm()).isEqualTo("Searched Concert 2");
    }
}
