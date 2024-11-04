package com.curateme.claco.review.domain.dto;

import java.math.BigDecimal;

import com.curateme.claco.review.domain.entity.TicketReview;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.11.04
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.11.04		   이 건		   최초 생성
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketReviewUpdateDto {

	// TicketReview id
	@NotNull
	private Long ticketReviewId;
	// 관람 좌석
	private String watchSit;
	// 별점
	private BigDecimal starRate;
	// 감상평
	private String content;

	public static TicketReviewUpdateDto fromEntity(TicketReview ticketReview) {
		return new TicketReviewUpdateDto(ticketReview.getId(), ticketReview.getWatchSit(), ticketReview.getStarRate(),
			ticketReview.getContent());
	}

}
