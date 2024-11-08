package com.curateme.claco.recommendation.domain.dto;

import com.curateme.claco.review.domain.dto.response.TicketReviewSummaryResponse;
import com.curateme.claco.concert.domain.dto.response.ConcertClacoBookResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendationConcertResponseV2 {

    private ConcertClacoBookResponse concertDetails;
    private TicketReviewSummaryResponse ticketReviewSummary;

    public static RecommendationConcertResponseV2 from(
        ConcertClacoBookResponse concertDetails,
        TicketReviewSummaryResponse ticketReviewSummary
    ) {
        return new RecommendationConcertResponseV2(concertDetails, ticketReviewSummary);
    }
}
