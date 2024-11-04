package com.curateme.claco.review.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.review.domain.entity.PlaceReview;
import com.curateme.claco.review.domain.entity.ReviewTag;
import com.curateme.claco.review.domain.entity.TicketReview;
import com.curateme.claco.review.domain.vo.ImageUrlVO;
import com.curateme.claco.review.domain.vo.PlaceCategoryVO;
import com.curateme.claco.review.domain.vo.TagCategoryVO;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketReviewInfoResponse {

	// 티켓 리뷰 id
	private Long ticketReviewId;
	// 콘서트 명
	private String concertName;
	// 관람 날짜
	private LocalDate watchDate;
	// 리뷰 남긴 날짜
	private LocalDate createdDate;
	// 관람 위치(공연장)
	private String watchPlace;
	// 관람 회차
	private String watchRound;
	// 러닝 타임
	private String runningTime;
	// 캐스팅
	private String castings;
	// 관람 좌석
	private String watchSit;
	// 관람 태그(공연 성격)
	private List<TagCategoryVO> concertTags;
	// 별점
	private BigDecimal starRate;
	// 관람평(본문)
	private String content;
	// 장소평
	private List<PlaceCategoryVO> placeReviews;
	// 리뷰 이미지
	private List<ImageUrlVO> imageUrlS;
	@Builder.Default
	private Boolean editor = true;

	public static TicketReviewInfoResponse fromTicketReview(TicketReview ticketReview) {
		TicketReviewInfoResponse response = new TicketReviewInfoResponse();
		response.ticketReviewId = ticketReview.getId();
		response.watchDate = ticketReview.getWatchDate();
		response.watchRound = ticketReview.getWatchRound();
		response.watchSit = ticketReview.getWatchSit();
		response.concertTags = ticketReview.getReviewTags().stream()
			.map(ReviewTag::getTagCategory)
			.map(TagCategoryVO::fromEntity)
			.toList();
		response.starRate = ticketReview.getStarRate();
		response.content = ticketReview.getContent();
		response.placeReviews = ticketReview.getPlaceReviews()
			.stream()
			.map(PlaceReview::getPlaceCategory)
			.map(PlaceCategoryVO::fromEntity)
			.toList();
		response.imageUrlS = ticketReview.getReviewImages().stream().map(ImageUrlVO::fromReviewImage).toList();
		response.concertName = ticketReview.getConcert().getPrfnm();
		response.watchPlace = ticketReview.getConcert().getFcltynm();
		response.runningTime = ticketReview.getConcert().getPrfruntime();
		response.castings = ticketReview.getCasting();
		response.createdDate = LocalDate.from(ticketReview.getCreatedAt());

		return response;
	}



}
