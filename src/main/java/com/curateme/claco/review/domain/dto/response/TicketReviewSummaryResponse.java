package com.curateme.claco.review.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TicketReviewSummaryResponse {

    @Schema(name = "concert_id")
    private Long concertId;

    @Schema(name = "created_at")
    private LocalDateTime createdAt;

    @Schema(name = "review_content")
    private String content;

    // Constructor
    public TicketReviewSummaryResponse(Long concertId, LocalDateTime createdAt, String content) {
        this.concertId = concertId;
        this.createdAt = createdAt;
        this.content = content;
    }
}
