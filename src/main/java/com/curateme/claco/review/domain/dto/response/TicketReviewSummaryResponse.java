package com.curateme.claco.review.domain.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TicketReviewSummaryResponse {
    private Long concertId;
    private LocalDateTime createdAt;
    private String content;

    // Constructor
    public TicketReviewSummaryResponse(Long concertId, LocalDateTime createdAt, String content) {
        this.concertId = concertId;
        this.createdAt = createdAt;
        this.content = content;
    }
}
