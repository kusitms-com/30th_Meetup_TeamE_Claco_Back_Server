package com.curateme.claco.review.domain.dto.response;

import com.curateme.claco.review.domain.entity.TicketReview;

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
public class TicketInfoResponse {

	private Long id;
	private String ticketImage;

	public static TicketInfoResponse fromEntity(TicketReview ticketReview) {
		return new TicketInfoResponse(ticketReview.getId(), ticketReview.getTicketImage());
	}

}
