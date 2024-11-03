package com.curateme.claco.review.domain.vo;

import com.curateme.claco.review.domain.entity.ReviewImage;
import com.curateme.claco.review.domain.entity.TicketReview;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageUrlVO {

	private String imageUrl;

	public static ImageUrlVO fromReviewImage(ReviewImage image) {
		return new ImageUrlVO(image.getImageUrl());
	}

	public static ImageUrlVO fromTicketImage(TicketReview ticketReview) {
		return new ImageUrlVO(ticketReview.getTicketImage());
	}

}
